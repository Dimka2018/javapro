import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {AskQuestionComponent} from "../page/ask-question/ask_question.component";
import {EditArticleComponent} from "../page/edit-article/edit-article.component";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MenuComponent} from "../wiget/menu/menu.component";
import {NgxEditorModule} from "ngx-editor";
import {HttpClientModule} from "@angular/common/http";
import {ViewArticleComponent} from "../page/view-article/view-article.component";
import {ContextViewComponent} from "../wiget/context-view/context-view.component";
import {LoginComponent} from "../page/login/login.component";
import {AdminConsole} from "../page/admin-console/admin-console";
import {InterviewModeComponent} from "../page/interview-mode/interview-mode";

@NgModule({
  declarations: [
    AppComponent,
    AskQuestionComponent,
    LoginComponent,
    EditArticleComponent,
    ViewArticleComponent,
    InterviewModeComponent,
    AdminConsole,
    MenuComponent,
    ContextViewComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    AppRoutingModule,
    NgxEditorModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
