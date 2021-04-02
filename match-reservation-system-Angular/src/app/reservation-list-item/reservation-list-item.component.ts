import { Component, OnInit, Input, Output } from '@angular/core';
import { TicketsService } from '../service/tickets.service';
import { DetailedReservation } from '../models/detailedReservation';
import { MatSnackBar } from '@angular/material/snack-bar';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import { Router } from '@angular/router';
import { EventEmitter } from 'protractor';


@Component({
  selector: 'app-reservation-list-item',
  templateUrl: './reservation-list-item.component.html',
  styleUrls: ['./reservation-list-item.component.css']
})
export class ReservationListItemComponent implements OnInit {
  stompClient = null;
  @Input() reservation:DetailedReservation;
  
  constructor(private ticketsService: TicketsService, 
    private _snackBar: MatSnackBar,
    private router: Router
    ) { 
      this.connect();
    }

  ngOnInit() {
  }

  future() {
    let current_date = new Date();
    return (new Date(this.reservation.match_date_time) > current_date)
  }

  cancelClick(){
    this.ticketsService.cancelReservation(this.reservation.seat_number, this.reservation.match_id).subscribe(()=>{
      this.openSnackBar("Reservation cancelled successfully");
      this.sendMsg();
      this.reservation=null;
      //this.router.navigate(['/profile']);
    }, err => {
      this.openSnackBar("Error cancelling reservation");
    })
  }

  canCancel() {
    if(this.calculateDiff(this.reservation.match_date_time) >= 3)
      return true;
    return false;
  }

  calculateDiff(sentDate) {
    let date1:any = new Date(sentDate);
    let date2:any = new Date();
    let diffDays:any = Math.floor((date1 - date2) / (1000 * 60 * 60 * 24));
    console.log(diffDays)
    return diffDays;
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

}
