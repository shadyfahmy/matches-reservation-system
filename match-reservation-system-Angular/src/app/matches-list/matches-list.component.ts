import { Component, OnInit } from '@angular/core';
import { EditMatchService } from '../service/edit-match.service'
import { CreateMatchService } from '../service/create-match.service'

@Component({
  selector: 'app-matches-list',
  templateUrl: './matches-list.component.html',
  styleUrls: ['./matches-list.component.css']
})
export class MatchesListComponent implements OnInit {

  matches = [];
  stadiums = [];
  constructor(private editMatchService: EditMatchService,
    private createMatchService: CreateMatchService) {
      this.editMatchService.getAllMatches().subscribe(data => {
        this.matches = data;
        console.log(this.matches)
  
      });
  
      this.createMatchService.listAllStadiums().subscribe(data => {
        this.stadiums = data
        console.log(this.stadiums)  
      });
     }

  ngOnInit() {

  }

}
