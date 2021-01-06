import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-match-list-item',
  templateUrl: './match-list-item.component.html',
  styleUrls: ['./match-list-item.component.css']
})
export class MatchListItemComponent implements OnInit {

  @Input() match:any;
  wantReserve = false;
  rows : any;
  seatPerRow : any;
  reserved : any;
  selected = []

  constructor() {

    this.rows = Array(this.match.number_of_rows).fill(0).map((x,i)=>i);
    this.seatPerRow = Array(this.match.seats_per_row).fill(0).map((x,i)=>i);
    this.reserved = [];

  }

  ngOnInit() {
  }

  reserveClick() {
    this.wantReserve = !this.wantReserve;
  }

  select(x) {
    console.log(x);
    if(this.selected.includes(x) == false)
      this.selected.push(x)
    else {
      let temp = this.selected.indexOf(x)
      this.selected.splice(temp,1)
    }
  }

}
