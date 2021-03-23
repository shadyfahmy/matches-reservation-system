import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomePageComponent } from './home-page/home-page.component';
import { LoginPageComponent } from './login-page/login-page.component';
import { ProfilePageComponent } from './profile-page/profile-page.component';
import { SignupPageComponent } from './signup-page/signup-page.component';
import { EditUserInfoPageComponent } from './edit-user-info-page/edit-user-info-page.component';
import { AdminstratorPageComponent } from './adminstrator-page/adminstrator-page.component';
import { MatchesPageComponent} from './matches-page/matches-page.component'
import { CreateStadiumPageComponent} from './create-stadium-page/create-stadium-page.component'

const routes: Routes = [
  { path: 'home', component: HomePageComponent },
  { path: 'login', component: LoginPageComponent },
  { path: 'edit', component: EditUserInfoPageComponent },
  { path: 'profile', component: ProfilePageComponent },
  { path: 'signup', component: SignupPageComponent },
  { path: 'matches', component: MatchesPageComponent },
  { path: 'stadium', component: CreateStadiumPageComponent },
  { path: 'adminstrator', component: AdminstratorPageComponent },
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: '**', redirectTo: '/home', pathMatch: 'full' },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
