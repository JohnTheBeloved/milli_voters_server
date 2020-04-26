import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map, tap } from 'rxjs/operators';
import { environment as config } from '../../environments/environment';
import { User } from '../_models';

@Injectable({ providedIn: 'root' })
export class UserService {
    constructor(private http: HttpClient) { }

    getAll() {
      return this.http.get<User[]>(`${config.apiUrl}/users`);
    }

    current() {
        return this.http.get<User>(`${config.apiUrl}/users/current`)
        .pipe(
          tap(o => console.log)
        );
    }

    register(user: User) {
        return this.http.post(`${config.apiUrl}/users/register`, user)
        .pipe(map((validUser: User) => {
          // store user details and jwt token in local storage to keep user logged in between page refreshes
          if (!validUser.uuid) {throw new Error('Valid token not received') };
          return true;
      }));
    }

    delete(id: number) {
        return this.http.delete(`${config.apiUrl}/users/${id}`);
    }
}
