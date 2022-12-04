import {Component, OnInit} from '@angular/core';
import {DocumentService} from "../../service/document.service";
import {Router} from "@angular/router";
import {UserService} from "../../service/user.service";
import {Notify} from "notiflix/build/notiflix-notify-aio";

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
      .subscribe(article => this.router.navigate(['/all-articles'])
        .then(() => this.router.navigate([`/edit-article/${article.id}`])),
        error => Notify.failure(error.message));
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
