import {Injectable} from '@angular/core';
import {Observable, of} from 'rxjs';
import {User} from '../Domain/user';
import {tap} from 'rxjs/internal/operators';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {DefaultService} from './default.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private url = '/auth/';
  private _cacheUser: Map<string, User> = new Map<string, User>();

  constructor(private _http: HttpClient, private _defaultService: DefaultService) {
    this.url = this._defaultService.url + this.url;
  }

  currentUser(): Observable<User> {
    return this._http.get<User>(this.url + 'user');
  }

  findOne(id: string): Observable<User> {
    if (this._cacheUser.has(id)) {
      return of(this._cacheUser.get(id));
    }
    return this._http.get<User>(this.url + 'api/user/' + id)
      .pipe(tap(it => this._cacheUser.set(id, it)));
  }

  logout(): Observable<Object> {
    return this._http.get(this._defaultService + '/signout');
  }

  login(user: User): Observable<User> {
    let data: string = "username=" + user.username + "&password=" + user.password + "&remember-me=" + true;

    return this._http.post<User>(this._defaultService.url + '/login', data, {headers: new HttpHeaders({'Content-Type': 'application/x-www-form-urlencoded'})});
  }
}
