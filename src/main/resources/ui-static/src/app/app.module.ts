import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {HomeComponent} from './Views/home/home.component';
import {LoginComponent} from './Views/login/login.component';
import {RouterModule} from '@angular/router';
import {routes} from './Views/routes';
import {BaseComponent} from './Views/base/base.component';
import {DashBoardComponent} from './Views/dash-board/dash-board.component';
import {DefaultService} from './Services/default.service';
import {AuthService} from './Services/auth.service';
import {UserService} from './Services/user.service';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule} from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    BaseComponent,
    DashBoardComponent
  ],
  imports: [
    BrowserModule, HttpClientModule,
    FormsModule, RouterModule.forRoot(routes)
  ],
  providers: [DefaultService, AuthService, UserService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
