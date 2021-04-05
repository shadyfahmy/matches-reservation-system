import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material';
import { FormControl, Validators  } from '@angular/forms';
import { Stadium } from '../models/stadium';
import { CreateMatchService } from '../service/create-match.service';
import { CreateStadiumService } from '../service/create-stadium.service';

@Component({
  selector: 'app-create-stadium-page',
  templateUrl: './create-stadium-page.component.html',
  styleUrls: ['./create-stadium-page.component.css']
})
export class CreateStadiumPageComponent implements OnInit {

  constructor(public createMatchService: CreateMatchService,
  public createStadiumService: CreateStadiumService,
  private _snackBar: MatSnackBar) 
  {
    this.listStadiums();
    this.stadium = new Stadium();
  }
  stadiums:Stadium[];
  stadium:Stadium;

  ngOnInit() {
  }
  listStadiums() {
    this.stadiums = [];
    this.createMatchService.listAllStadiums().subscribe(stadiums => {
      this.stadiums = stadiums;
    })
  }
  cityFormControl = new FormControl('', [
    Validators.required
  ]);
  nameFormControl = new FormControl('', [
    Validators.required
  ]);
  noOfRowsFormControl = new FormControl('', [
    Validators.required,
    Validators.min(2),
    Validators.max(10)
  ]);
  seatsPerRowFormControl = new FormControl('', [
    Validators.required,
    Validators.min(4),
    Validators.max(15)
  ]);
  createStadium(){
    for (var s of this.stadiums)
    {
      if(s.name == this.stadium.name)
        {
          this.openSnackBar("A Stadium with this name already exists!");
          return;
        }
    }

    this.stadium.id = 0;
    this.createStadiumService.createStadium(this.stadium).subscribe(() => {
      this.openSnackBar("Stadium created!");
      this.listStadiums();
      this.stadium = new Stadium();

      // this.router.navigate(['/home']);
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
