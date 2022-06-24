import {Component, OnInit} from '@angular/core';
import {DocumentService} from "../../service/document.service";
import {Router} from "@angular/router";
import {UserService} from "../../service/user.service";

@Component({
  selector: 'menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.scss']
})
export class MenuComponent {

  expanded: Boolean = false;

  constructor(private docService: DocumentService, private userService: UserService, private router: Router) {
  }

  createArticle() {
    this.docService.createArticle()
      .subscribe(article => this.router.navigate([`/edit-article/${article.id}`]));
  }

  switchExpand() {
    this.expanded = !this.expanded
  }

  isAuthenticated() {
    return this.userService.authenticated;
  }

  logout() {
    this.userService.logout()
      .subscribe(() => this.userService.refreshAuthentication())
  }

  hasPermission(permission: string) {
    return this.userService.hasPermission(permission)
  }
}
