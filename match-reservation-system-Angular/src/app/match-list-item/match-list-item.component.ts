import { Component, OnInit, Input } from '@angular/core';
import { Stadium } from '../models/stadium';
import { Match } from '../models/match';
import { ActiveAccountService } from '../service/active-account.service'
import { MatSnackBar } from '@angular/material/snack-bar';
import { TicketsService } from '../service/tickets.service'
import { from } from 'rxjs';
import { Ticket } from '../models/ticket';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';

@Component({
  selector: 'app-match-list-item',
  templateUrl: './match-list-item.component.html',
  styleUrls: ['./match-list-item.component.css']
})
export class MatchListItemComponent implements OnInit {

  stompClient = null;
  @Input() match:Match;
  @Input() stadium: Stadium;
  wantReserve = false;
  confirm = false;
  rows : any;
  seatPerRow : any;
  reserved = [];
  selected = [];
  cardNum: string;
  pin: string;
  subscribed = false;

  constructor(private activeAccountService : ActiveAccountService,
    private _snackBar: MatSnackBar,
    private ticketsService: TicketsService) {

      this.connect();
  }

  ngOnInit() {
    console.log(this.match)
    console.log(this.stadium)
    this.rows = Array(this.stadium.number_of_rows).fill(0).map((x,i)=>i);
    this.seatPerRow = Array(this.stadium.seats_per_row).fill(0).map((x,i)=>i);
    this.reserved = [];
    this.selected = [];
    console.log(this.rows)
    this.getReserved();
    console.log(this.reserved)
  }

  reserveClick() {
    if(!this.subscribed){
      this.sub();
      this.subscribed = true;
    }
    if(this.wantReserve){
      this.selected = []
    }
    this.wantReserve = !this.wantReserve;
  }

  select(x) {
    if(this.future() && this.isFan()) {
      console.log(x);
      if(this.selected.includes(x) == false && this.reserved.includes(x) == false)
        this.selected.push(x)
      else {
        let temp = this.selected.indexOf(x)
        this.selected.splice(temp,1)
      }
    }
  }

  confirmClick(){
    this.confirm = !this.confirm;
  }

  future() {
    let current_date = new Date();
    return (new Date(this.match.match_date_time) > current_date)
  }

  isFan() {
    return (this.activeAccountService.isActive && this.activeAccountService.getUser().user_type == "Fan")
  }

  isGuest() {
    return (!this.activeAccountService.isActive())
  }

  okClick(){
    if(this.cardNum.length == 14 && this.pin.length == 4)
      this.reserve();
    else{
      this.openSnackBar("Invalid card information");
    }
  }

  reserve() {

    this.ticketsService.reserveTicket(this.selected, this.match.match_id).subscribe(()=>{
      this.openSnackBar("Tickets reserved successfully");
      this.wantReserve=false;
      this.confirm=false;
      this.selected = []
      this.sendMsg();
    }, err => {
      this.openSnackBar(err.error);
    });

  }

  getReserved() {
    this.ticketsService.getReservedSeats(this.match.match_id).subscribe(data => {
      console.log(data)
      for(let i = 0; i < data.length; i++){
        this.reserved.push(data[i].seat_number)
      }
    })
  }

  openSnackBar(msg: string) {
    this._snackBar.open(msg, 'OK', {
      duration: 2000,
      horizontalPosition: 'center',
      verticalPosition: 'bottom',
    });
  }

  connect() {
    var socket = new SockJS('http://localhost:8080/ws');
    this.stompClient = Stomp.over(socket);
    console.log(this.stompClient)
    this.stompClient.connect();
  }

  sendMsg() {
    this.stompClient.send("/app/hello", {}, JSON.stringify({'content': 'refreeesh'}));
  }

  sub() {
    let that = this;
    this.stompClient.subscribe('/topic/greetings', function (greeting) {
      //console.log("recieveeeed");
      that.ngOnInit();
    });
  }
  

}
