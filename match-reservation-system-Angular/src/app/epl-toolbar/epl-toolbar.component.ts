import { Component, OnInit } from '@angular/core';
import { ActiveAccountService } from '../service/active-account.service'
import { Router } from '@angular/router';

@Component({
  selector: 'app-epl-toolbar',
  templateUrl: './epl-toolbar.component.html',
  styleUrls: ['./epl-toolbar.component.css']
})
export class EplToolbarComponent implements OnInit {

  public loggedIn: Boolean;
  constructor(private router:Router,
    public activeAccountService: ActiveAccountService) { }

  ngOnInit() {
    this.initializePage();
  }

  initializePage() {
    this.loggedIn = this.activeAccountService.isActive();
    console.log(this.loggedIn);
  }

  logout() {
    this.activeAccountService.logout();
    this.initializePage();
    this.router.navigate(['/home']);
  }

}
