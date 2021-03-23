import { stringify } from '@angular/compiler/src/util';
import { Component, OnInit } from '@angular/core';
import { FormControl, Validators  } from '@angular/forms';
import { Match } from '../models/match';
import { Team } from '../models/team';
import { CreateMatchService } from '../service/create-match.service';
import { EditMatchService } from '../service/edit-match.service';

import { customDateValidator } from './custom-date-validatior'
import { MatSnackBar } from '@angular/material';




@Component({
  selector: 'app-matches-page',
  templateUrl: './matches-page.component.html',
  styleUrls: ['./matches-page.component.css']
})

export class MatchesPageComponent implements OnInit {
  teams:any;
  stadiums:any;
  match:Match;
  matchSelected:boolean;
  matchEdited:Match;
  matchBeforeEdit:Match;
  matches: Match[];
  selectedMatchId:number;
  date = new Date(new Date().getFullYear()+ 0,0,0,1,0,0,0).toISOString().slice(0, 16);
  minDate = new Date(new Date().getFullYear()+ 0,0,0,1,0,0,0);
  maxDate = new Date(new Date().getFullYear()+ 5,0,0,0,0,0,0);
  constructor(public createMatchService: CreateMatchService,
    public editMatchService: EditMatchService,
    private _snackBar: MatSnackBar) {
    this.listTeams();
    this.listMatches();
    this.listStadums();
    this.match = new Match();
    console.log(this.match);
    this.matchEdited = new Match();
    this.matchBeforeEdit = new Match();
    this.matchSelected = false;
   }

  homeTeamFormControl = new FormControl('', [
    Validators.required,
  ]);
  awayTeamFormControl = new FormControl('', [
    Validators.required,
  ]);
  stadiumFormControl = new FormControl('', [
    Validators.required,
  ]);
  refereeFormControl = new FormControl('', [
    Validators.required
  ]);
  linesman1FormControl = new FormControl('', [
    Validators.required
  ]);
  linesman2FormControl = new FormControl('', [
    Validators.required
  ]);
  matchDateTimeFormControl = new FormControl('', [
    Validators.required,
    customDateValidator
    ]);
  selectedMatchIdFormControl= new FormControl('', [
    Validators.required
  ]);edii
  editedRefereeFormControl = new FormControl('', [
    Validators.required
  ]);
  editedLinesman1FormControl = new FormControl('', [
    Validators.required
  ]);
  editedLinesman2FormControl = new FormControl('', [
    Validators.required
  ]);
  editedMatchDateTimeFormControl = new FormControl('', [
    Validators.required,
    customDateValidator
  ]);
  ngOnInit() {
  }
  listMatches() {
    this.matches = [];
    this.editMatchService.listMatches().subscribe(matches => {
      this.matches = matches;
    })
    this.matchEdited = new Match();
    this.selectedMatchId = 0;

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
    if(this.match.away_team == this.match.home_team)
    {
        this.openSnackBar("Home and Away Teams Must be different!");
        return;
    }
    this.match.match_id = 0;
    console.log(this.match)
    this.createMatchService.createMatch(this.match).subscribe(() => {
      this.openSnackBar("Match created!");
      this.listMatches();
      this.match = new Match();

      // this.router.navigate(['/home']);
    }, err => {
      this.openSnackBar(err.error);
    });
  }

  editMatch(){
    if(this.matchEdited.away_team == this.matchEdited.home_team)
    {
        this.openSnackBar("Home and Away Teams Must be different!");
        return;
    }
    this.editMatchService.editMatch(this.matchEdited).subscribe(() => {
      this.openSnackBar("Match Edited!");
      this.listMatches();
      this.matchEdited = new Match();
      this.selectedMatchId = null;
      // this.router.navigate(['/home']);
    }, err => {
      this.openSnackBar(err.error);
    });

  }

  matchSelectedFn()
  {
    return this.matchSelected;
  }
  populateEditedMatch()
  {

    if(this.selectedMatchId == 0)
      return;
    for ( var m of this.matches)
    {
      if (m.match_id == this.selectedMatchId)
      {
        this.matchSelected = true;

        var mTemp = new Match();
        Object.assign(mTemp, m);
        mTemp.match_date_time = mTemp.match_date_time.replace(" ","T")
        Object.assign(this.matchEdited, mTemp);
        Object.assign(this.matchBeforeEdit, mTemp);
        return;

      }
    }
  }
  edited()
  {
    var edited = this.matchEdited.home_team != this.matchBeforeEdit.home_team ||
    this.matchEdited.away_team != this.matchBeforeEdit.away_team ||
    this.matchEdited.stadium_id != this.matchBeforeEdit.stadium_id ||
    this.matchEdited.main_refree != this.matchBeforeEdit.main_refree ||
    this.matchEdited.linesman1 != this.matchBeforeEdit.linesman1 ||
    this.matchEdited.linesman2 != this.matchBeforeEdit.linesman2 ||
    this.matchEdited.match_date_time != this.matchBeforeEdit.match_date_time;;
    // console.log(edited)
    return edited && this.matchEdited.home_team && this.matchEdited.away_team && this.matchEdited.stadium_id ;
  }
  
  openSnackBar(msg: string) {
    this._snackBar.open(msg, 'Okay', {
      duration: 2000,
      horizontalPosition: 'center',
      verticalPosition: 'bottom',
    });
  }
}
