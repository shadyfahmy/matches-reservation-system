import { Component, OnInit } from '@angular/core';
import { User } from '../models/user';
import { RegistrationService } from '../service/registration.service';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit {

  user: User;
  
  constructor(public registrationService: RegistrationService,) { }

  ngOnInit() {
  }

  signIn() {
    if (this.user.username && this.user.pw) {
      this.registrationService.signIn(this.user).subscribe(res => {
        console.log(res);
      });
    }
  }

}
