import {AfterViewInit, Component, ElementRef, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {Editor, toDoc, Toolbar} from "ngx-editor";
import {DocumentService} from "../../service/document.service";
import {Article} from "../../model/article";
import {ActivatedRoute} from "@angular/router";
import {ArticleMapper} from "../../mapper/article.mapper";
import {ImageService} from "../../service/image.service";
import {fromEvent} from "rxjs";
import { filter, debounceTime, distinctUntilChanged, tap } from 'rxjs/operators';

@Component({
  selector: 'edit-article',
  templateUrl: './edit-article.component.html',
  styleUrls: ['./edit-article.component.scss'],
})
export class EditArticleComponent implements AfterViewInit, OnInit, OnDestroy {

  @ViewChild('article_title', {static: true})
  articleTitle: any;

  article: Article = new Article();
  html: string = '';
  editor!: Editor;
  toolbar: Toolbar = [
    ["bold", "italic"],
    ["underline", "strike"],
    ["code", "blockquote"],
    ["ordered_list", "bullet_list"],
    [{heading: ["h1", "h2", "h3", "h4", "h5", "h6"]}],
    ["link", "image"],
    ["text_color", "background_color"],
    ["align_left", "align_center", "align_right", "align_justify"]
  ];

  constructor(private docService: DocumentService, private imageService: ImageService,
              private route: ActivatedRoute, private mapper: ArticleMapper) {
  }

  ngAfterViewInit(): void {
    fromEvent(this.articleTitle.nativeElement,'input')
      .pipe(
        filter(Boolean),
        debounceTime(1000),
        // @ts-ignore
        distinctUntilChanged(),
        tap(() => this.onTitleChanged()))
      .subscribe();
  }

  onChange(html: string) {
    let doc = toDoc(html);
    this.prepareContent(doc.content).then(() => {
      let articleToUpdate = this.mapper.docToArticle(doc, this.article.id, this.article.title);
      this.article = articleToUpdate;
      //this.html = this.mapper.articleToDoc(articleToUpdate)
      this.docService.saveDoc(articleToUpdate)
        .subscribe();
    });
  }

  onTitleChanged() {
    this.docService.saveDoc(this.article)
      .subscribe();
  }

  ngOnInit(): void {
    this.editor = new Editor();
    this.article.id = this.route.snapshot.paramMap.get('id')!.toString()
    this.docService.getArticle(this.article.id)
      .subscribe(article => {
        this.article = article
        this.html = this.mapper.articleToDoc(article)
      })
  }

  private async prepareContent(content: any) {
    for (let element of content) {
      await this.prepareElement(element);
    }
  }

  private async prepareElement(element: any) {
    if (element.type === 'image') {
      if (element.attrs.src.startsWith('http')) {
        await this.imageService.saveImage(element.attrs.src, this.article.id!).toPromise()
          .then(image => element.attrs.src = `/api/images/${image.id}`)
      }
    } else if (element.content) {
      for (let contentElement of element.content) {
        await this.prepareElement(contentElement);
      }
    }
  }

  ngOnDestroy(): void {
    this.editor.destroy();
  }

}
