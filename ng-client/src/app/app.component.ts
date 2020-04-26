import { Component, ViewChild, OnInit } from '@angular/core';
import { MilliVotersComponent } from './components/milli-voters/milli-voters.component';

import { Router } from '@angular/router';
import { User } from './_models/user';
import { AuthenticationService } from './_services/authentication.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'milli-voters';
  currentUser: User;
  constructor(
    private router: Router,
    private authenticationService: AuthenticationService
  ) {
      this.authenticationService.currentUser.subscribe(x => this.currentUser = x);
  }
  @ViewChild(MilliVotersComponent ) child: MilliVotersComponent ;

  counter = 0;
  ngOnInit() {
  }

  public increaseCounter(this) {
    this.counter++;
  }
  private getMilliVotersCounterValue(){
  this.counter = this.child.getCounter();
  }

  logout() {
    this.authenticationService.logout();
    this.router.navigate(['/login']);
  }
}
