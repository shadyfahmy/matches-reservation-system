import { stringify } from '@angular/compiler/src/util';
import { Component, OnInit } from '@angular/core';
import { FormControl, Validators,FormGroup  } from '@angular/forms';
import { Match } from '../models/match';
import { Team } from '../models/team';
import { CreateMatchService } from '../service/create-match.service';
import {customDateValidator} from './custom-date-validatior'
@Component({
  selector: 'app-matches-page',
  templateUrl: './matches-page.component.html',
  styleUrls: ['./matches-page.component.css']
})

export class MatchesPageComponent implements OnInit {
  teams:any;
  stadiums:any;
  match:Match;
  date = new Date(new Date().getFullYear()+ 0,0,0,1,0,0,0).toISOString().slice(0, 16);
  minDate = new Date(new Date().getFullYear()+ 0,0,0,1,0,0,0);
  maxDate = new Date(new Date().getFullYear()+ 5,0,0,0,0,0,0);
  constructor(public createMatchService: CreateMatchService) {
    this.listTeams();
    this.listStadums()
    this.match = new Match();
   }

  homeTeamFormControl = new FormControl('Home Team', [
    Validators.required,
    this.differentTeamsValidator
  ]);
  awayTeamFormControl = new FormControl('Away Team', [
    Validators.required,
    this.differentTeamsValidator
  ]);
  refereeFormControl = new FormControl('Main Referee', [
    Validators.required
  ]);
  linesman1FormControl = new FormControl('Linesman 1', [
    Validators.required
  ]);
  linesman2FormControl = new FormControl('Linesman 2', [
    Validators.required
  ]);
  matchDateTimeFormControl = new FormControl('Match Date Time', [
    Validators.required,
    customDateValidator
    ]);
  ngOnInit() {
  }
  listTeams() {
    this.teams = [];
    this.createMatchService.listAllTeams().subscribe(teams => {
      this.teams = teams;
      console.log(teams);
    })
    console.log(this.date);

  }
  listStadums() {
    this.stadiums = [];
    this.createMatchService.listAllStadiums().subscribe(stadiums => {
      this.stadiums = stadiums;
      console.log(stadiums);
    })
  }
  createMatch(){

    this.match = new Match();
  }
  differentTeamsValidator(form: FormGroup  ){
    if (!form)
        return null;
    
    console.log("gs",form)
    if(form.value  )
        return null;
    return {
        sameTeam:{
            "team":form.value
        }
    }
}
}
