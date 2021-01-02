import { Component, OnInit } from '@angular/core';
import { User } from '../models/user';
import { FormControl, Validators } from '@angular/forms';
import { UserService } from '../service/user.service';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material';
import { ActiveAccountService } from '../service/active-account.service';

@Component({
  selector: 'app-edit-user-info-page',
  templateUrl: './edit-user-info-page.component.html',
  styleUrls: ['./edit-user-info-page.component.css']
})
export class EditUserInfoPageComponent implements OnInit {

  user: User;
  date = new Date(new Date().getFullYear() - 8, 11, 31);
  minDate: Date;
  maxDate: Date;
  editPassword: Boolean;
  newPasswrod: string;
  fnameFormControl = new FormControl('First name', [
    Validators.required
  ]);
  lnameFormControl = new FormControl('Last name', [
    Validators.required
  ]);
  constructor(private router: Router,
    public userService: UserService,
    public activeAccountService:ActiveAccountService,
    private _snackBar: MatSnackBar) { }

  ngOnInit() {
    this.initializePage();
  }
  initializePage(){
    if(!this.activeAccountService.isActive())
    {
      this.router.navigate(['/home']);
    }
    this.user = this.activeAccountService.getUser();
    this.newPasswrod = null;
    const currentYear = new Date().getFullYear();
    this.minDate = new Date(currentYear - 80, 0, 1);
    this.maxDate = new Date(currentYear - 8, 11, 31);
  }

  editUser() {
    const year = new Intl.DateTimeFormat('en', { year: 'numeric' }).format(this.date);
    const month = new Intl.DateTimeFormat('en', { month: 'numeric' }).format(this.date);
    const day = new Intl.DateTimeFormat('en', { day: '2-digit' }).format(this.date);
    this.user.dob = year + "-" + month + "-" + day;
    if(this.newPasswrod) {
      this.editPassword = true;
      console.log("edit Password")
      this.user.pw = this.newPasswrod;
    }
    else {
      this.editPassword = false;
    }
    this.userService.editUserInfo(this.user, this.editPassword).subscribe(res => {
      this.activeAccountService.setActiveUser(res[0]['token'], res[1]['user']);
      this.initializePage();
      this.openSnackBar("Information has been editted successfully");
      
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
