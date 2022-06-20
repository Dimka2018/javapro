import {Component} from '@angular/core';
import {Question} from "../../model/question";
import {Router} from "@angular/router";
import {QuestionService} from "../../service/question.service";

@Component({
  selector: 'ask-question',
  templateUrl: './ask_question.component.html',
  styleUrls: ['./ask_question.component.scss']
})
export class AskQuestionComponent {

  public isSend: Boolean = false
  public contact: string = ''
  public question: string = ''

  constructor(private questionService: QuestionService, private router: Router) {
  }

  setIsSend(isSend: Boolean) {
    this.isSend = isSend;
  }

  sendQuestion() {
    this.isSend = true
    let question = new Question()
    question.contact = this.contact
    question.text = this.question
    this.questionService.sendQuestion(question)
      .subscribe(() => this.setIsSend(true))
  }

  refreshQuestion() {
    this.question = ''
    this.isSend = false
  }

  openAllArticles() {
    this.router.navigate([`/all-articles`])
  }

}
