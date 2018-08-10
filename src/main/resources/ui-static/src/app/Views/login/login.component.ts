import {Component, OnInit} from '@angular/core';
import {User} from '../../Domain/user';
import {UserService} from '../../Services/user.service';
import {AuthService} from '../../Services/auth.service';

@Component({
  selector: 't-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  public user: User = new User();

  constructor(private _userService: UserService, private _authService: AuthService) {
  }

  ngOnInit() {
  }

  login() {
    this._userService.login(this.user).subscribe(it => {
      this._authService.setAuth(it, 'true');
    });

  }
}
