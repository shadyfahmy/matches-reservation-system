import { Component, OnInit } from '@angular/core';
import { ActiveAccountService } from '../service/active-account.service'
import { Router } from '@angular/router';
import { User } from '../models/user';

@Component({
  selector: 'app-epl-toolbar',
  templateUrl: './epl-toolbar.component.html',
  styleUrls: ['./epl-toolbar.component.css']
})
export class EplToolbarComponent implements OnInit {

  public loggedIn: Boolean;
  public user:User;
  constructor(private router:Router,
    public activeAccountService: ActiveAccountService) { }

  ngOnInit() {
    this.initializePage();
  }

  initializePage() {
    this.loggedIn = this.activeAccountService.isActive();
    this.user = this.activeAccountService.getUser();
    console.log(this.loggedIn);
  }

  logout() {
    this.activeAccountService.logout();
    this.initializePage();
    this.router.navigate(['/home']);
  }

}
