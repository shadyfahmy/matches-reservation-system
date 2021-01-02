import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {

  private host:string = 'http://localhost:8080/api/auth';

  constructor(public http: HttpClient) { }

  // User Registration serivce
  signUp(user: User) {
    const httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    }

    return this.http.post(this.host + "/signup", JSON.stringify(
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

  signIn(user: User) {
    const httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    }

    return this.http.post(this.host + "/signin", JSON.stringify(
      {
        "username": user.username,
        "pw": user.pw
      }), httpOptions);
  }
}
