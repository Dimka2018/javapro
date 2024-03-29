import {Component} from '@angular/core';
import {Router} from "@angular/router";
import {UserService} from "../../service/user.service";
import {User} from "../../model/user";

@Component({
  selector: 'login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {

  public user: User = new User

  constructor(private userService: UserService, private router: Router) {
  }

  login() {
    this.userService.login(this.user)
      .subscribe(() => {
        this.userService.refreshAuthentication();
        this.router.navigate([`/articles`])
      })
  }

}
