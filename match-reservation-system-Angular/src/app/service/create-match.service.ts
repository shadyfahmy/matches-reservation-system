import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
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


  createMatch(match: Match) {
    const httpOptions = {
      headers: new HttpHeaders({ 
        'Content-Type': 'application/json',
        'authorization': this.activeAccountService.getToken()
      })
    }
    console.log("trying to", match)
    return this.http.post(this.host + "/createMatch", JSON.stringify(
    {
      "match_id": 0,
      "home_team": match.home_team,
      "away_team": match.away_team,
      "match_date_time": match.match_date_time,
      "main_refree": match.main_refree,
      "linesman1": match.linesman1,
      "linesman2": match.linesman2,
      "stadium_id": match.stadium_id
    }), httpOptions);
  }
}
