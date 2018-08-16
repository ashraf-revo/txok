import {Injectable} from '@angular/core';
import {NavigationEnd, Router} from '@angular/router';
import {AuthService} from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class DefaultService {
  public url = '';
  private _lastRoute: NavigationEnd = null;
  private protectedUrl: string[] = ['home','account','profile'];
  private unProtectedUrl: string[] = ['','login','signup'];

  constructor() {
  }

  set lastRoute(value: NavigationEnd) {
    this._lastRoute = value;
  }

  isAccessible(router: Router, authService: AuthService): boolean {
    if (authService.getAuthUser().isAuth == null) {
      return true;
    }
    else if(this._lastRoute!=null&&authService.getAuthUser().isAuth != null&&this.unProtectedUrl.indexOf(this._lastRoute.url.split('/')[1]) !== -1&& authService.getAuthUser().isAuth === 'true')
    {
      console.log("i route it " +this._lastRoute.url);
      router.navigate(['/', 'home']);
      return false;
    }
    else if (this._lastRoute!=null&&authService.getAuthUser().isAuth === 'false' && this.protectedUrl.indexOf(this._lastRoute.url.split('/')[1]) !== -1) {
      console.log("i route it " +this._lastRoute.url);
      router.navigate(['/', 'login']);
      return false;
    }
    return true;
  }

  get lastRoute(): NavigationEnd {
    return this._lastRoute;
  }


  handle(router: Router, authService: AuthService) {
    router.events.subscribe(it => {
      if (it instanceof NavigationEnd) {
        this.lastRoute = it;
        this.isAccessible(router, authService);
      }
    });
    authService.onChange().subscribe(it => {
      this.isAccessible(router, authService);
    });
  }
}
