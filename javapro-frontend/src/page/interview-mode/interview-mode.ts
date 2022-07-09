import {Component} from '@angular/core';
import {DocumentService} from "../../service/document.service";
import {Article} from "../../model/article";
import {Router} from "@angular/router";
import {UserService} from "../../service/user.service";

@Component({
  selector: 'all-articles',
  templateUrl: './interview-mode.html',
  styleUrls: ['./interview-mode.scss']
})
export class InterviewModeComponent {

  public articles: Article[] = [];
  public articleId: string = ''

  constructor(private docService: DocumentService) {
  }

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
}
