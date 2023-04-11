import {Component, OnInit} from '@angular/core';
import {DocumentService} from "../../service/document.service";
import {Article} from "../../model/article";
import {Router} from "@angular/router";
import {UserService} from "../../service/user.service";

@Component({
  selector: 'all-articles',
  templateUrl: './interview-mode.html',
  styleUrls: ['./interview-mode.scss']
})
export class InterviewModeComponent implements OnInit {

  public articles: Article[] = [];
  public articleId: string = ''

  constructor(private docService: DocumentService, private userService: UserService, private router: Router) {
  }

  ngOnInit() {
    this.refreshArticles();
  };

  setArticleId(id: string) {
    this.articleId = id;
  }

  find(text: string) {
    this.docService.findArticle(text)
      .subscribe(articles => this.articles = articles)
  }

  focus(event: any) {
    event.target.focus()
  }

  editArticle(id: string) {
    this.router.navigate([`/edit-article/${id}`])
  }

  deleteArticle(id: string) {
    this.docService.deleteArticle(id)
      .subscribe(() => this.refreshArticles())
  }

  refreshArticles() {
    return this.docService.getArticleList()
      .subscribe(articles => {
        this.articles = articles
      });
  }

  hasPermission(permission: string): boolean {
    return this.userService.hasPermission(permission)
  }
}
