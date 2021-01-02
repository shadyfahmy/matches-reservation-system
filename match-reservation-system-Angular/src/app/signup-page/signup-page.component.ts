import { Component, OnInit } from '@angular/core';
import { User } from '../models/user';
import { FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-signup-page',
  templateUrl: './signup-page.component.html',
  styleUrls: ['./signup-page.component.css']
})
export class SignupPageComponent implements OnInit {

  user: User;
  date = new Date(new Date().getFullYear() - 8, 11, 31);
  minDate: Date;
  maxDate: Date;
  emailFormControl = new FormControl('email', [
    Validators.required,
    Validators.email
  ]);
  usernameFormControl = new FormControl('username', [
    Validators.required
  ]);
  fnameFormControl = new FormControl('First name', [
    Validators.required
  ]);
  lnameFormControl = new FormControl('Last name', [
    Validators.required
  ]);
  passwordFormControl = new FormControl('password', [
    Validators.required
  ]);
  constructor() { }

  ngOnInit() {
    this.user = new User();
    this.user.gender = "Male";
    this.user.city = "Giza";
    this.user.user_type = "Fan";
    const currentYear = new Date().getFullYear();
    this.minDate = new Date(currentYear - 80, 0, 1);
    this.maxDate = new Date(currentYear - 8, 11, 31);
  }

  signUp() {
    console.log(this.date);
    const ye = new Intl.DateTimeFormat('en', { year: 'numeric' }).format(this.date);
    const mo = new Intl.DateTimeFormat('en', { month: 'numeric' }).format(this.date);
    const da = new Intl.DateTimeFormat('en', { day: '2-digit' }).format(this.date);
    console.log(ye,mo,da)
    console.log(this.user);
  }

}
