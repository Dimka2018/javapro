import {Component} from '@angular/core';
import {DocumentService} from "../../service/document.service";
import {Router} from "@angular/router";
import {UserService} from "../../service/user.service";

@Component({
  selector: 'menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.scss']
})
export class MenuComponent {

  public expanded: Boolean = false;

  constructor(private docService: DocumentService, private userService: UserService, private router: Router) {
  }

  createArticle() {
    this.docService.createArticle()
      .subscribe(article => this.router.navigate([`/edit-article/${article.id}`]));
  }

  switchExpand() {
    this.expanded = !this.expanded
  }

  isAuthorized() {
    return this.userService.user
  }

  logout() {
    this.userService.logout()
  }

  hasPermission(permission: string) {
    return this.userService
  }
}
