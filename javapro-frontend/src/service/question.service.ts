import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Image} from "../model/image";
import {Question} from "../model/question";

@Injectable({providedIn: 'root'})
export class QuestionService {

  constructor(private http: HttpClient) {}

  public sendQuestion(question: Question): Observable<void> {
    return this.http.post<void>('/api/question', question)
  }

}
