import { Injectable } from '@angular/core';
import { Stadium } from '../models/stadium';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ActiveAccountService } from './active-account.service';

@Injectable({
  providedIn: 'root'
})
export class CreateStadiumService {

  constructor(public http: HttpClient,
  public activeAccountService: ActiveAccountService)
  { }
  
  private host:string = 'http://localhost:8080/api/stadium';

  createStadium(stadium: Stadium) {
    const httpOptions = {
      headers: new HttpHeaders({ 
        'Content-Type': 'application/json',
        'authorization': this.activeAccountService.getToken()
      })
    }
    console.log("trying to", stadium)
    return this.http.post(this.host + "/createStadium", JSON.stringify(
    {
      "id": 0,
      "city": stadium.city,
      "name": stadium.name,
      "number_of_rows": stadium.number_of_rows,
      "seats_per_row": stadium.seats_per_row,
    }), httpOptions);
  }

}
