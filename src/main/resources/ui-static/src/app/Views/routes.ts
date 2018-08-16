import {Routes} from "@angular/router";
import {BaseComponent} from "./base/base.component";
import {BaseInComponent} from "./base-in/base-in.component";
import {BaseOutComponent} from "./base-out/base-out.component";
import {LoginComponent} from "./login/login.component";
import {SignupComponent} from "./signup/signup.component";
import {DashBoardComponent} from "./dash-board/dash-board.component";
import {HomeComponent} from "./home/home.component";
import {ProfileComponent} from "./profile/profile.component";
import {AccountEditComponent} from "./account-edit/account-edit.component";

export const routes: Routes = [
  {
    path: '', component: BaseComponent, children: [
      {
        path: '', component: BaseOutComponent, children: [
          {path: '', component: DashBoardComponent},
          {path: 'login', component: LoginComponent},
          {path: 'signup', component: SignupComponent}
        ]
      }, {
        path: '', component: BaseInComponent, children: [
          {path: 'home', component: HomeComponent},
          {path: 'profile/:id', component: ProfileComponent},
          {path: 'account/edit', component: AccountEditComponent},
        ]
      }, {path: '**', redirectTo: ''}]
  },
];
