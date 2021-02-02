import { Component, OnInit } from '@angular/core';
import { User } from '../models/user';
import { AdminstratorService } from '../service/adminstrator.service';

@Component({
  selector: 'app-adminstrator-page',
  templateUrl: './adminstrator-page.component.html',
  styleUrls: ['./adminstrator-page.component.css']
})
export class AdminstratorPageComponent implements OnInit {
  u:User = new User();
  pendingUsers:User[];
  users:User[];

  constructor(public adminstratorService: AdminstratorService) { }

  ngOnInit() {
  }

  initPending() {
    this.pendingUsers = [
      {
        pw : "1234",
        fname : "Mouhammadkhier",
        lname : "Shaheen",
        dob : new Date().getFullYear(),
        city : "6th of october",
        gender : "mal",
        mail : "eng.mhd.sh@gmail.com",
        user_type : "fan",
        address : "fifth district",
        approved :  1,
        username: "Darknight"
      },
      {
        pw : "asdf",
        fname : "amr",
        lname : "abdulba2y",
        dob : new Date().getFullYear(),
        city : "giza haram",
        gender : "mal",
        mail : "amr.amr.amr@gmail",
        user_type : "fan",
        address : "haram giza giza",
        approved :  1,
        username: "amr1234"
      },
      {
        pw : "asdf",
        fname : "abdullah",
        lname : "Mouhammad",
        dob : new Date().getFullYear(),
        city : "7elowan",
        gender : "mal",
        mail : "mhd.Abdullah@gmail.com.eg.egu",
        user_type : "fan",
        address : "7elowan 7elowan 7elowan",
        approved :  1,
        username: "asdf3"
      },
    ]
  }

  initUsers() {
    this.users = [
      {
        pw : "1234",
        fname : "Mouhammadkhier",
        lname : "Shaheen",
        dob : new Date().getFullYear(),
        city : "6th of october",
        gender : "mal",
        mail : "eng.mhd.sh@gmail.com",
        user_type : "fan",
        address : "fifth district",
        approved :  1,
        username: "Darknight"
      },
      {
        pw : "asdf",
        fname : "amr",
        lname : "abdulba2y",
        dob : new Date().getFullYear(),
        city : "giza haram",
        gender : "mal",
        mail : "amr.amr.amr@gmail",
        user_type : "fan",
        address : "haram giza giza",
        approved :  1,
        username: "amr1234"
      },
      {
        pw : "asdf",
        fname : "abdullah",
        lname : "Mouhammad",
        dob : new Date().getFullYear(),
        city : "7elowan",
        gender : "mal",
        mail : "mhd.Abdullah@gmail",
        user_type : "fan",
        address : "7elowan 7elowan 7elowan",
        approved :  1,
        username: "asdf3"
      },
    ]
  }


  listPending() {
    this.users = [];
    //this.initPending();
    this.adminstratorService.listPending().subscribe(pendingUsers => {
      this.pendingUsers =  pendingUsers;
    });
    console.log(this.users);
  }

  listAccounts() {
    this.pendingUsers = [];
    //this.initUsers();
    this.adminstratorService.listAllAccounts().subscribe(users => {
      this.users = users;
    })
    console.log(this.users);
  }

  onDelete(user) {
    this.adminstratorService.delete(user).subscribe();
    this.users = this.users.filter(u => u.username !== user.username);
  }

  accept(user) {
    this.adminstratorService.accept(user).subscribe();
    this.pendingUsers = this.pendingUsers.filter(u => u.username !== user.username);
  }

  reject(user) {
    this.adminstratorService.reject(user).subscribe();
    //this.pendingUsers = this.pendingUsers.filter(u => u.username !== user.username);
  }

}
