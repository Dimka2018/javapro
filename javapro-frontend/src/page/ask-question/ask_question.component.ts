import {Component} from '@angular/core';
import {Question} from "../../model/question";
import {Router} from "@angular/router";
import {QuestionService} from "../../service/question.service";
import {Notify} from "notiflix";

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

  sendQuestion() {
    if (this.contact.match(/^\S+@\S+\.\S+$/)) {
      let question = new Question()
      question.contact = this.contact
      question.text = this.question
      this.questionService.sendQuestion(question)
        .subscribe(() => this.isSend = true)
    } else {
      Notify.failure("Invalid email")
    }
  }

  openAllArticles() {
    this.router.navigate([`/all-articles`])
  }

}
