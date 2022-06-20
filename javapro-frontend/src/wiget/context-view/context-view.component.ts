import {ElementRef, Input, OnDestroy, OnInit, QueryList, ViewChildren} from '@angular/core';
import { ViewChild } from '@angular/core';
import { Component } from '@angular/core';
import {Article} from "../../model/article";
import {DocumentService} from "../../service/document.service";
import {ActivatedRoute} from "@angular/router";
import {ArticleMapper} from "../../mapper/article.mapper";
import {Editor} from "ngx-editor";

@Component({
  selector: 'context-view',
  templateUrl: './context-view.component.html',
  styleUrls: ['./context-view.component.scss']
})
export class ContextViewComponent implements OnInit, OnDestroy  {

  @Input()
  articleId: string = '';

  editor: Editor = new Editor();
  html: string = '';

  constructor(private docService: DocumentService, private route: ActivatedRoute, private mapper: ArticleMapper) {}

  ngOnInit(): void {
    this.docService.getArticle(this.articleId)
      .subscribe(article => {
        this.html = this.mapper.articleToDoc(article)
      })
  }

  ngOnDestroy(): void {
    this.editor.destroy();
  }

}
