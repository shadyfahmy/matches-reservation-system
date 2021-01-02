import { Component, OnInit } from '@angular/core';
import { User } from '../models/user';
import { FormControl, Validators } from '@angular/forms';
import { RegistrationService } from '../service/registration.service';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material';

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
  constructor(private router: Router,
    public registrationService: RegistrationService,
    private _snackBar: MatSnackBar) { }

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
    const year = new Intl.DateTimeFormat('en', { year: 'numeric' }).format(this.date);
    const month = new Intl.DateTimeFormat('en', { month: 'numeric' }).format(this.date);
    const day = new Intl.DateTimeFormat('en', { day: '2-digit' }).format(this.date);
    this.user.dob = year + "-" + month + "-" + day;
    this.registrationService.signUp(this.user).subscribe(() => {
      this.openSnackBar("Registration completed, wait for admin approval");
      this.router.navigate(['/home']);
    }, err => {
      this.openSnackBar(err.error);
    });
  }

  openSnackBar(msg: string) {
    this._snackBar.open(msg, 'Okay', {
      duration: 2000,
      horizontalPosition: 'center',
      verticalPosition: 'bottom',
    });
  }

}
