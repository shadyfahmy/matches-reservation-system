import { Injectable } from '@angular/core';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class ActiveAccountService {

  constructor() { }

  isActive(): Boolean {
    let token = localStorage.getItem("token");
    let user = localStorage.getItem("user");
    return (token != null && user != null)
  }

  logout(): void {
    localStorage.removeItem("user");
    localStorage.removeItem("token");
  }

  setActiveUser(token: string, user: User): void {
    localStorage.setItem("token", JSON.stringify(token));
    localStorage.setItem("user", JSON.stringify(user));
  }

  getUser(): User{
    return JSON.parse(localStorage.getItem("user"));
  }

  getToken(): string {
    let token = JSON.parse(localStorage.getItem("token"));
    if (!token) token = "";
    return token
  }
}