import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ActiveAccountService } from './active-account.service';
import { Ticket } from '../models/ticket';


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

}
