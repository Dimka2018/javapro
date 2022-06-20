import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {User} from "../model/user";

@Injectable({providedIn: 'root'})
export class UserService {

  public user?: User;

  constructor(private http: HttpClient) {
  }

  public login(user: User): Observable<User> {
    return this.http.post<User>('/api/users/login', user)
  }

  public logout(): Observable<void> {
    return this.http.get<void>('/api/users/logout')
      .pipe(o => {
        this.user = undefined
        return o
      })
  }

  public hasPermission(permission: string): boolean {
    return !!this.user && !!this.user.permissions && this.user.permissions.includes(permission);
  }

}
