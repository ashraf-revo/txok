import {Routes} from "@angular/router";
import {HomeComponent} from "./home/home.component";
import {LoginComponent} from "./login/login.component";
import {BaseComponent} from "./base/base.component";
import {DashBoardComponent} from "./dash-board/dash-board.component";

export const routes: Routes = [
  {
    path: '', component: BaseComponent, children: [
      {path: '', component: DashBoardComponent},
      {path: 'home', component: HomeComponent},
      {path: 'login', component: LoginComponent}
    ]
  },
];
