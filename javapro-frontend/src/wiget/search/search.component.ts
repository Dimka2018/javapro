import {ElementRef, QueryList, ViewChildren} from '@angular/core';
import { ViewChild } from '@angular/core';
import { Component } from '@angular/core';
import {Article} from "../../model/article";
import {DocumentService} from "../../service/document.service";
import {ActivatedRoute} from "@angular/router";
import {ArticleMapper} from "../../mapper/article.mapper";

@Component({
  selector: 'search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.scss']
})
export class SearchComponent {

  @ViewChild('search_window', {static: false})
  set content(content: ElementRef) {
    if(content) {
      content.nativeElement.focus();
    }
  }

  public expanded: Boolean = false;
  public articles: Article[] = [];
  public articleId: string = ''

  constructor(private docService: DocumentService) {}

  setArticleId(id: string) {
    this.articleId = id;
  }

  find(text: string) {
    this.docService.findArticle(text)
      .subscribe(articles => this.articles = articles)
  }

  switchArrow() {
    this.expanded = !this.expanded
  }
}
