import {Article} from "../model/article";
import {Injectable} from "@angular/core";

@Injectable({providedIn: 'root'})
export class ArticleMapper {

  articleToDoc(article: Article): any {
    let doc: any = {
      type: 'doc',
    }
    doc.content = article.content
    return doc;
  }

  docToArticle(doc: any, id?: string, title?: string): Article {
    let article = new Article();
    article.id = id;
    article.title = title;
    article.content = doc.content
    return article;
  }

}
