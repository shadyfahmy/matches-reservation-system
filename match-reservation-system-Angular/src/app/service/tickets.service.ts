import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ActiveAccountService } from './active-account.service';
import { Ticket } from '../models/ticket';
import { Match } from '../models/match';
import { DetailedReservation } from '../models/detailedReservation';


@Injectable({
  providedIn: 'root'
})
export class TicketsService {

  constructor(public http: HttpClient,
    public activeAccountService: ActiveAccountService) { }

  private host:string = 'http://localhost:8080/api/ticket';

  reserveTicket(tickets, match: number) {
    console.log(tickets)
    const httpOptions = {
      headers: new HttpHeaders({ 
        'Content-Type': 'application/json',
        'authorization': this.activeAccountService.getToken()
      })
    }
    return this.http.post(this.host + "/reserve", JSON.stringify(
      {
        "match":match,
        "tickets":tickets
      }), httpOptions);
  }

  getReservedSeats(match) {
    const httpOptions = {
      headers: new HttpHeaders({ 
        'Content-Type': 'application/json',
        'authorization': this.activeAccountService.getToken()
      })
    }
    return this.http.get<Ticket[]>(this.host + "/getreserved?match="+match, httpOptions);

  }

  getReservations() {
    const httpOptions = {
      headers: new HttpHeaders({ 
        'Content-Type': 'application/json',
        'authorization': this.activeAccountService.getToken()
      })
    }
    console.log(this.activeAccountService.getToken())
    return this.http.get<DetailedReservation[]>(this.host + "/getreservations", httpOptions);
  }

  cancelReservation(seat: number, match: number) {
    const httpOptions = {
      headers: new HttpHeaders({ 
        'Content-Type': 'application/json',
        'authorization': this.activeAccountService.getToken()
      })
    }
    return this.http.delete(this.host + "/cancelreservation?match="+match+"&seat="+seat, httpOptions);
  }

}
