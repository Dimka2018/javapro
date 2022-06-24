import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {User} from "../model/user";

@Injectable({providedIn: 'root'})
export class UserService {

  authenticated = false;
  permissions: string[] = []

  constructor(private http: HttpClient) {
    this.refreshAuthentication()
  }

  public login(user: User): Observable<void> {
    let formData = new FormData();
    // @ts-ignore
    formData.append("username", user.username)
    // @ts-ignore
    formData.append("password", user.password)
    return this.http.post<void>("/api/login", formData);
  }

  public logout(): Observable<void> {
    return this.http.get<void>('/api/logout');
  }

  public refreshAuthentication() {
    this.http.get<boolean>('/api/user/authentication')
      .subscribe(result => this.authenticated = result);
    this.http.get<string[]>('/api/user/permissions')
      .subscribe(result => this.permissions = result)
  }

  public hasPermission(permission: string): boolean {
    return this.permissions.includes(permission);
  }

}
