import {Component} from '@angular/core';
import {FormControl} from '@angular/forms';
import {ResultGitVersion, VersionControllerService} from './segregator-ts-api';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {

  constructor(private versionControllerService: VersionControllerService) {
    versionControllerService.getVersionUsingGET().subscribe( (res: ResultGitVersion ) => {
      console.log(res);
      this.version = res.result['buildVersion'];
    });

  }

  title = 'language-segregator';
  toppings = new FormControl();

  toppingList: string[] = ['Extra cheese', 'Mushroom', 'Onion', 'Pepperoni', 'Sausage', 'Tomato'];

  version = '';



}
