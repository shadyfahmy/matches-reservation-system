import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../models/user';
import { RegistrationService } from '../service/registration.service';
import { ActiveAccountService } from '../service/active-account.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit {

  user: User;

  constructor(private router:Router,
    public registrationService: RegistrationService,
    private activeAccountService: ActiveAccountService,
    private _snackBar: MatSnackBar) { }

  ngOnInit() {
    this.user = new User();
    this.user.username = '';
    this.user.pw = '';
  }

  signIn() {
    if (this.user.username != '' && this.user.pw != '') {
      this.registrationService.signIn(this.user).subscribe(res => {
        this.activeAccountService.setActiveUser(res[0]['token'], res[1]['user']);
        this.openSnackBar("Welcome !");
        this.router.navigate(['/home']);
      }, err => {
        this.openSnackBar(err.error);
      });
    }
  }
  signUp() {
    this.router.navigate(['/signup']);
  }

  openSnackBar(msg: string) {
    this._snackBar.open(msg, 'Okay', {
      duration: 2000,
      horizontalPosition: 'center',
      verticalPosition: 'bottom',
    });
  }

}
