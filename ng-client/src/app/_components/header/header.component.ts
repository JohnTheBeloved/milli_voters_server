import { Component, OnInit, } from '@angular/core';
import { User } from 'src/app/_models';
import { UserService, AuthenticationService } from 'src/app/_services';

@Component({ selector: 'header-nav', templateUrl: './header.component.html' })
export class HeaderComponent  implements OnInit {

  currentUser: User = null;

  constructor(private authenticationService: AuthenticationService){

  }
  ngOnInit(): void {
    this.authenticationService.currentUser.subscribe(x => this.currentUser = x);
  }

  onSignOut() {
    this.authenticationService.logout();
  }

}
