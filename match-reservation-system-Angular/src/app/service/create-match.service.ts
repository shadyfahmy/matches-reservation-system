import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Match } from '../models/match';
import { Team } from '../models/team';
import { Stadium } from '../models/stadium';
import { Observable } from 'rxjs';
import { ActiveAccountService } from './active-account.service';



@Injectable({
  providedIn: 'root'
})
export class CreateMatchService {
  constructor(public http: HttpClient,
    public activeAccountService: ActiveAccountService) { }

  private host:string = 'http://localhost:8080/api/match';

  listAllTeams(): Observable<Team[]> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'authorization': this.activeAccountService.getToken()
      })
    }

    return this.http.get<Team[]>(this.host + "/getTeams", httpOptions);
  }
  listAllStadiums(): Observable<Stadium[]> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'authorization': this.activeAccountService.getToken()
      })
    }

    return this.http.get<Stadium[]>(this.host + "/getStadiums", httpOptions);
  }
}
