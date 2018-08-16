import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../Services/auth.service";
import {UserService} from "../../Services/user.service";
import {User} from "../../Domain/user";
import {tap} from "rxjs/internal/operators";

@Component({
  selector: 't-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css']
})
export class NavComponent implements OnInit {
  public user: User;

  constructor(private _authService: AuthService, private _userService: UserService) {

  }

  ngOnInit() {
    this.user = this._authService.getAuthUser().user;
    this._authService.onChange().subscribe(it => {
      this.user = it.user
    });
  }

  logout() {
    this._userService.logout().pipe(tap(it => this._authService.setAuth(null, 'false'))).subscribe()
  }
}
