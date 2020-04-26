import { UserService } from 'src/app/_services';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { map, mergeMap, tap } from 'rxjs/operators';
import { JwtHelperService } from '@auth0/angular-jwt';
import { environment as config } from '../../environments/environment';

import { User } from '../_models';
import { Router } from '@angular/router';

@Injectable({ providedIn: 'root' })
export class AuthenticationService {
    private currentUserSubject: BehaviorSubject<User>;
    public currentUser: Observable<User>;

    constructor(private http: HttpClient, private userService: UserService,
                private router: Router) {
        this.currentUserSubject = new BehaviorSubject<User>(JSON.parse(localStorage.getItem('currentUser')));
        this.currentUser = this.currentUserSubject.asObservable();
    }

    public get currentUserValue(): User {
        return this.currentUserSubject.value;
    }

    public setCurrentUser(user: User) {
      user.token = localStorage.getItem('access_token');
      localStorage.setItem('currentUser', JSON.stringify(user));
      this.currentUserSubject.next(user);
    }

    private saveAccessToken(accessToken: string) {
      localStorage.setItem('access_token', accessToken);
    }

    login(username, password) {
        return this.http.post<any>(`${config.apiUrl}/users/login`, { username, password })
            .pipe(
              tap(({ access_token }) => { if (!access_token) { throw new Error('Access token not received'); }}),
              tap(({ access_token }) => { this.saveAccessToken(access_token); })
            );
    }

    logout() {
        // remove user from local storage and set current user to null
        localStorage.removeItem('access_token');
        localStorage.removeItem('currentUser');
        this.currentUserSubject.next(null);
        this.router.navigate(['/login']);
    }
}

