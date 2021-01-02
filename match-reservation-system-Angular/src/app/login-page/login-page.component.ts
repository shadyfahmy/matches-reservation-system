import { Component, OnInit } from '@angular/core';
import { User } from '../models/user';
import { RegistrationService } from '../service/registration.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit {

  user: User;

  constructor(public registrationService: RegistrationService,
    private _snackBar: MatSnackBar) { }

  ngOnInit() {
    this.user = new User();
    this.user.username = '';
    this.user.pw = '';
  }

  signIn() {
    console.log(this.user)
    if (this.user.username != '' && this.user.pw != '') {
      this.registrationService.signIn(this.user).subscribe(res => {
        console.log(res);
      }, err => {
        console.log(err);
        this.openSnackBar(err.error);
      });
    }
  }

  openSnackBar(msg: string) {
    this._snackBar.open(msg, 'Okay', {
      duration: 2000,
      horizontalPosition: 'center',
      verticalPosition: 'bottom',
    });
  }

}
