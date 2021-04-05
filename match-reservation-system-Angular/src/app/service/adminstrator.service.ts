import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { User } from '../models/user';
import { Observable } from 'rxjs';
import { ActiveAccountService } from './active-account.service';

@Injectable({
  providedIn: 'root'
})
export class AdminstratorService {

  private host:string = 'http://localhost:8080/api/adminstrator';

  constructor(public http: HttpClient,
    public activeAccountService: ActiveAccountService) { }

  listPending(): Observable<User[]> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'authorization': this.activeAccountService.getToken()
      })
    }

    return this.http.get<User[]>(this.host + "/listPending",httpOptions);
  }

  listAllAccounts(): Observable<User[]> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'authorization': this.activeAccountService.getToken()
      })
    }

    return this.http.get<User[]>(this.host + "/listAllAccounts", httpOptions);
  }

  accept(user: User){
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'authorization': this.activeAccountService.getToken()
      })
    }

    return this.http.post(this.host + "/accept", JSON.stringify(
      {
        "fname": user.fname,
        "lname": user.lname,
        "dob": user.dob,
        "username": user.username,
        "gender": user.gender,
        "mail": user.mail,
        "pw": user.pw,
        "user_type": user.user_type,
        "address": user.address,
        "city": user.city,
        "approved": user.approved
      }), httpOptions);
  }

  delete(user: User){
    let httpParams = new HttpParams().set("username", user.username);
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'authorization': this.activeAccountService.getToken()
      }),
      params: httpParams
    }

    return this.http.delete(this.host + "/delete/", httpOptions);
  }
}
