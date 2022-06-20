import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Image} from "../model/image";

@Injectable({providedIn: 'root'})
export class ImageService {

  constructor(private http: HttpClient) {}

  public saveImage(src: string, articleId: string): Observable<Image> {
    return this.http.post<Image>('/api/image', {src: src, articleId: articleId})
  }

}
