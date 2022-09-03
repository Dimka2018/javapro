import {Component, OnInit} from '@angular/core';
import {DocumentService} from "../../service/document.service";
import {Router} from "@angular/router";
import {UserService} from "../../service/user.service";
import {QuestionService} from "../../service/question.service";
import {Question} from "../../model/question";

@Component({
  selector: 'admin-console',
  templateUrl: './admin-console.html',
  styleUrls: ['./admin-console.scss']
})
export class AdminConsole implements OnInit {

  questionsOpened = false;
  questions: Question[] = [];

  constructor(private docService: DocumentService, private questionService: QuestionService) {
  }

  downloadHistory() {
    this.docService.downloadHistory();
  }

  applyHistory(event: any) {
    this.docService.applyHistory(event.target.files[0])
      .subscribe()
  }

  ngOnInit(): void {
    this.questionService.getQuestions()
      .subscribe(questions => this.questions = questions)
  }

  toggleQuestionsOpened() {
    this.questionsOpened = !this.questionsOpened;
  }

  deleteQuestion(id: string) {
    this.questionService.deleteQuestion(id)
      .subscribe(() => this.questionService.getQuestions()
        .subscribe(questions => this.questions = questions))
  }

}
