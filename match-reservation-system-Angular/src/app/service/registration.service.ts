import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {

  private host:string = 'http://localhost:8080/api/v1';

  constructor(public http: HttpClient,) { }

  // User Registration serivce
  signUp(user: User) {
    const httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    }

    return this.http.post(this.host + "/signup", JSON.stringify(
    {
      "firstName": user.firstName,
      "lastName": user.lastName,
      "dateOfBirth": user.bdate,
      "username": user.username,
      "gender": user.gender,
      "email": user.email,
      "password": user.password,
      "role": user.role,
      "address": user.address,
      "city": user.city
    }), httpOptions);
  }

  signIn(user: User) {
    const httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    }

    return this.http.post(this.host + "/signin", JSON.stringify(
      {
        "username": user.username,
        "password": user.password
      }), httpOptions);
  }
}
