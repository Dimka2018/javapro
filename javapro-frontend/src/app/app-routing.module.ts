import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {AskQuestionComponent} from "../page/ask-question/ask_question.component";
import {EditArticleComponent} from "../page/edit-article/edit-article.component";
import {ViewArticleComponent} from "../page/view-article/view-article.component";
import {LoginComponent} from "../page/login/login.component";
import {AdminConsole} from "../page/admin-console/admin-console";
import {InterviewModeComponent} from "../page/interview-mode/interview-mode";

const routes: Routes = [
  { path: 'ask-question', component: AskQuestionComponent },
  { path: 'login', component: LoginComponent },
  { path: 'articles', component: InterviewModeComponent },
  { path: 'edit-article/:id', component: EditArticleComponent },
  { path: 'articles/:id', component: ViewArticleComponent },
  { path: 'admin-console', component: AdminConsole },
  { path: '', redirectTo: '/articles', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
