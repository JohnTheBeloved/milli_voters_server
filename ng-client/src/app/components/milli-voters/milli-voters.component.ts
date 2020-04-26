import {Component} from '@angular/core';

@Component({
  selector: 'milli-voters',
  templateUrl: './milli-voters.component.html',
})
export class MilliVotersComponent {
  counter = 0;

  public getCounter(){
    return this.counter;
  }
}
