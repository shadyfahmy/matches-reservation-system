import { Component, OnInit } from '@angular/core';
import { ActiveAccountService } from '../service/active-account.service'

@Component({
  selector: 'app-profile-page',
  templateUrl: './profile-page.component.html',
  styleUrls: ['./profile-page.component.css']
})
export class ProfilePageComponent implements OnInit {
  user_type: string;
  constructor(private activeAccountService: ActiveAccountService) { }

  ngOnInit() {
    this.user_type = this.activeAccountService.getUser().user_type;
  }

}
