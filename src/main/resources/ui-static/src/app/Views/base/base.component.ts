import {Component, OnInit} from '@angular/core';
import {DefaultService} from '../../Services/default.service';
import {AuthService} from '../../Services/auth.service';
import {Router} from '@angular/router';
import {UserService} from '../../Services/user.service';

@Component({
  selector: 't-base',
  templateUrl: './base.component.html',
  styleUrls: ['./base.component.css']
})
export class BaseComponent implements OnInit {


  constructor(public _router: Router,
              private _userService: UserService,
              private _authService: AuthService,
              private _defaultService: DefaultService) {
    this._defaultService.handle(this._router, this._authService);
  }


  ngOnInit() {
    this._userService.currentUser().subscribe(it => {
      this._authService.setAuth(it, 'true');
      this._router.navigate(['/home']);
    }, it => this._authService.setAuth(null, 'false'));
  }
}
