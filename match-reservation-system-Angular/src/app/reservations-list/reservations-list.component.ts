import { Component, OnInit } from '@angular/core';
import { TicketsService } from '../service/tickets.service';

@Component({
  selector: 'app-reservations-list',
  templateUrl: './reservations-list.component.html',
  styleUrls: ['./reservations-list.component.css']
})
export class ReservationsListComponent implements OnInit {

  reservations = [];

  constructor(private ticketsService:TicketsService) { }

  ngOnInit() {
    this.ticketsService.getReservations().subscribe(data => {
      console.log(data)
      this.reservations = data;
    })
  }

}
