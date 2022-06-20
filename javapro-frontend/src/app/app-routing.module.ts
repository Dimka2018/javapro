import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {AllArticlesComponent} from "../page/all_articles/all-articles.component";
import {AskQuestionComponent} from "../page/ask-question/ask_question.component";
import {EditArticleComponent} from "../page/edit-article/edit-article.component";
import {ViewArticleComponent} from "../page/view-article/view-article.component";
import {LoginComponent} from "../page/login/login.component";

const routes: Routes = [
  { path: 'ask-question', component: AskQuestionComponent },
  { path: 'login', component: LoginComponent },
  { path: 'all-articles', component: AllArticlesComponent },
  { path: 'edit-article/:id', component: EditArticleComponent },
  { path: 'articles/:id', component: ViewArticleComponent },
  { path: '', redirectTo: '/all-articles', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
