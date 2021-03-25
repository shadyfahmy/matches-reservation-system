import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from "@angular/common/http";
import { ReactiveFormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HomePageComponent } from './home-page/home-page.component';
import { LoginPageComponent } from './login-page/login-page.component';
import { ProfilePageComponent } from './profile-page/profile-page.component';
import { MatButtonModule } from '@angular/material/button';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatTabsModule } from '@angular/material/tabs';
import { MatCardModule } from '@angular/material/card';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { SignupPageComponent } from './signup-page/signup-page.component';
import { MatRadioModule } from '@angular/material/radio';
import { MatSelectModule } from '@angular/material/select';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { EditUserInfoPageComponent } from './edit-user-info-page/edit-user-info-page.component';
import { MatchesListComponent } from './matches-list/matches-list.component';
import { ReservationsListComponent } from './reservations-list/reservations-list.component';
import { MatchListItemComponent } from './match-list-item/match-list-item.component';
import { ReservationListItemComponent } from './reservation-list-item/reservation-list-item.component';
import { AdminstratorPageComponent } from './adminstrator-page/adminstrator-page.component';
import { MatchesPageComponent } from './matches-page/matches-page.component';
import { CreateStadiumPageComponent } from './create-stadium-page/create-stadium-page.component';

@NgModule({
  declarations: [
    AppComponent,
    HomePageComponent,
    LoginPageComponent,
    ProfilePageComponent,
    SignupPageComponent,
    EditUserInfoPageComponent,
    MatchesListComponent,
    ReservationsListComponent,
    MatchListItemComponent,
    ReservationListItemComponent,
    AdminstratorPageComponent,
    MatchesPageComponent,
    CreateStadiumPageComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    MatButtonModule,
    MatToolbarModule,
    MatTabsModule,
    MatCardModule,
    MatInputModule,
    MatFormFieldModule,
    MatIconModule,
    MatSnackBarModule,
    MatRadioModule,
    MatSelectModule,
    MatGridListModule,
    MatDatepickerModule,
    MatNativeDateModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
