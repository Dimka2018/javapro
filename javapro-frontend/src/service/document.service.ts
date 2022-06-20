import {Injectable} from "@angular/core";
import {Article} from "../model/article";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({providedIn: 'root'})
export class DocumentService {

  constructor(private http: HttpClient) {}

  public saveDoc(article: Article): Observable<void> {
    return this.http.put<void>("/api/article", article);
  }

  public createArticle(): Observable<Article> {
    return this.http.post<Article>("/api/article", {});
  }

  public getArticleList(): Observable<Article[]> {
    return this.http.get<Article[]>("/api/articles")
  }

  public getArticle(id: string): Observable<Article> {
    return this.http.get<Article>(`/api/articles/${id}`)
  }

  public deleteArticle(id: string): Observable<void> {
    return this.http.delete<void>(`/api/articles/${id}`)
  }

  public findArticle(text: string): Observable<Article[]> {
    return this.http.get<Article[]>(`/api/articles?text=${text}`)
  }

}
