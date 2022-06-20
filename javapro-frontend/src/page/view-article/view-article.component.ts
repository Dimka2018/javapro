import {Component, OnDestroy, OnInit} from '@angular/core';
import {Editor, toDoc, Toolbar} from "ngx-editor";
import {DocumentService} from "../../service/document.service";
import {Article} from "../../model/article";
import {ActivatedRoute} from "@angular/router";
import {ArticleMapper} from "../../mapper/article.mapper";

@Component({
  selector: 'view-article',
  templateUrl: './view-article.component.html',
  styleUrls: ['./view-article.component.scss'],
})
export class ViewArticleComponent implements OnInit, OnDestroy {

  article: Article = new Article();
  editor!: Editor;
  html: string = '';

  constructor(private docService: DocumentService, private route: ActivatedRoute, private mapper: ArticleMapper) {}

  ngOnInit(): void {
    this.editor = new Editor();
    this.article.id = this.route.snapshot.paramMap.get('id')!.toString()
    this.docService.getArticle(this.article.id)
      .subscribe(article => {
        this.article = article;
        this.html = this.mapper.articleToDoc(article)
      })
  }

  ngOnDestroy(): void {
    this.editor.destroy();
  }

}
