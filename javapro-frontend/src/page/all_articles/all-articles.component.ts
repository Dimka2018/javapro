import {Component} from '@angular/core';
import {DocumentService} from "../../service/document.service";
import {Article} from "../../model/article";
import {Router} from "@angular/router";
import {UserService} from "../../service/user.service";

@Component({
  selector: 'all-articles',
  templateUrl: './all-articles.component.html',
  styleUrls: ['./all-articles.component.scss']
})
export class AllArticlesComponent {
  public articles: Article[] = [];

  constructor(private docService: DocumentService, private userService: UserService, private router: Router) {
  }

  ngOnInit() {
    this.refreshArticles();
  };

  openArticle(id: string) {
    this.router.navigate([`/articles/${id}`])
  }

  refreshArticles() {
    return this.docService.getArticleList()
      .subscribe(articles => this.articles = articles);
  }

  changeArticle(id: string) {
    this.router.navigate([`/edit-article/${id}`])
  }

  deleteArticle(id: string) {
    this.docService.deleteArticle(id)
      .subscribe(() => this.refreshArticles())
  }

  hasPermission(permission: string): boolean {
    return this.userService.hasPermission(permission)
  }
}
