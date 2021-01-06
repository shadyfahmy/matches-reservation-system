import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-reservation-list-item',
  templateUrl: './reservation-list-item.component.html',
  styleUrls: ['./reservation-list-item.component.css']
})
export class ReservationListItemComponent implements OnInit {

  @Input() reservation:any;
  
  constructor() { }

  ngOnInit() {
  }

}
