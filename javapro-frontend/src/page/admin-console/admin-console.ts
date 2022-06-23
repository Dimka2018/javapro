import {Component} from '@angular/core';
import {DocumentService} from "../../service/document.service";
import {Article} from "../../model/article";
import {Router} from "@angular/router";
import {UserService} from "../../service/user.service";

@Component({
  selector: 'admin-console',
  templateUrl: './admin-console.html',
  styleUrls: ['./admin-console.scss']
})
export class AdminConsole {

  constructor(private docService: DocumentService, private userService: UserService, private router: Router) {
  }

  downloadHistory() {
    this.docService.downloadHistory();
  }

  applyHistory(event: any) {
    this.docService.applyHistory(event.target.files[0])
      .subscribe()
  }

}
