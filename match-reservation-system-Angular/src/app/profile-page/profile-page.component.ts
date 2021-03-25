import { Component, OnInit } from '@angular/core';
import { ActiveAccountService } from '../service/active-account.service'
import { Router } from '@angular/router';

@Component({
  selector: 'app-profile-page',
  templateUrl: './profile-page.component.html',
  styleUrls: ['./profile-page.component.css']
})
export class ProfilePageComponent implements OnInit {
  user_type: string;
  constructor(private router:Router,
    private activeAccountService: ActiveAccountService) { }

  ngOnInit() {
    if(this.activeAccountService.isActive())
      this.user_type = this.activeAccountService.getUser().user_type;
    else
      this.router.navigate(['/home']);
      
  }

}
