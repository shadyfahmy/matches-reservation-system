import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from '../models/user';
import { ActiveAccountService } from './active-account.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private host: string = 'http://localhost:8080/api/user';
  constructor(public http: HttpClient,
    public activeAccountService: ActiveAccountService) { }

  editUserInfo(user: User, editPassword: Boolean) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'authorization': this.activeAccountService.getToken()
      })
    }
    console.log(httpOptions);
    let editPasswordParameter;
    if (editPassword) {
      editPasswordParameter = 'true';
    }
    else {
      editPasswordParameter = 'false';
    }
    return this.http.put(this.host + "/edit/" + editPasswordParameter, JSON.stringify(
      {
        "fname": user.fname,
        "lname": user.lname,
        "dob": user.dob,
        "gender": user.gender,
        "pw": user.pw,
        "user_type": user.user_type,
        "address": user.address,
        "city": user.city,
      }), httpOptions);
  }
}
