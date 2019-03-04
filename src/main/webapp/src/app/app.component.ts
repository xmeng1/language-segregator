import {Component, OnChanges, OnInit, SimpleChanges, ViewChild} from '@angular/core';
import {FormControl} from '@angular/forms';
import {DocOptions, ResultGitVersion, VersionControllerService} from './segregator-ts-api';
import {NGXLogger} from "ngx-logger";
import localeCode from "iso-639-1";
import {Observable} from "rxjs";
import {COMMA, ENTER} from '@angular/cdk/keycodes';
import {ElementRef} from '@angular/core';
import {MatAutocompleteSelectedEvent, MatChipInputEvent, MatAutocomplete} from '@angular/material';
import {map, startWith} from 'rxjs/operators';
import {MatSelectChange} from "@angular/material/typings/esm5/select";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

  constructor(private versionControllerService: VersionControllerService, private logger: NGXLogger) {
    versionControllerService.getVersionUsingGET().subscribe((res: ResultGitVersion) => {
      this.logger.debug("get the version result", res);
      this.version = res.result['buildVersion'];
    });
  }

  title = 'language-segregator';
  toppings = new FormControl();

  toppingList: string[] = [];
  descToLang: { [key: string]: string; } = {};

  initialLang() {
    for (let n in DocOptions.LangListEnum) {
      let description = localeCode.getName(n.toLocaleLowerCase());
      if (n.toLocaleLowerCase() == "ast") {
        description = "Asturian";
      } else if (n.toLocaleLowerCase() == "zhcn") {
        n = "ZH_CN";
        description = "Chinese(Simplified)";
      } else if (n.toLocaleLowerCase() == "zhtw") {
        n = "ZH_TW";
        description = "Chinese(Traditional)";
      } else if (n.toLocaleLowerCase() == "unknown") {
        // don't use the unknown language for user selection
        continue;
      }
      if (description.length == 0) {
        this.logger.info("cannot get the language description: ", n);
      }
      this.toppingList.push(description);
      this.descToLang[description] = n;
    }
    this.toppingList.sort();
    this.logger.debug(localeCode.getName('en'));
    // this.logger.debug(this.toppingList)

  }

  selectionChange(selectedData: MatSelectChange) {
    this.logger.debug("selectionChange ", selectedData);
    this.selectedLang = [];
    let data = selectedData.value as Array<String>;
    data.forEach((element) => {
      this.selectedLang.push(Array.of(element));
    });
    // this.fruits.concat(selectedData.value as Array<String>)
    // (selectedData.value as Array<String>).map(k => this.fruits.join(k));
  }

  ngOnInit() {
    this.initialLang();
  }

  selectable = true;
  removable = false;
  fruitCtrl = new FormControl();
  selectedLang: string[] = [];

  @ViewChild('fruitInput') fruitInput: ElementRef<HTMLInputElement>;
  @ViewChild('auto') matAutocomplete: MatAutocomplete;

  add(event: MatChipInputEvent): void {
    // Add fruit only when MatAutocomplete is not open
    // To make sure this does not conflict with OptionSelected Event
    if (!this.matAutocomplete.isOpen) {
      const input = event.input;
      const value = event.value;
      // Add our fruit
      if ((value || '').trim()) {
        this.selectedLang.push(value.trim());
      }
      // Reset the input value
      if (input) {
        input.value = '';
      }
      this.fruitCtrl.setValue(null);
    }
  }

  remove(fruit: string): void {
    const index = this.selectedLang.indexOf(fruit);

    if (index >= 0) {
      this.selectedLang.splice(index, 1);
    }
    this.toppings.reset()
    this.toppings.value = this.selectedLang;
  }

  version = '';

  onFileSelected() {
    const inputNode: any = document.querySelector('#file');

    if (typeof (FileReader) !== 'undefined') {
      const reader = new FileReader();

      reader.onload = (file: any) => {
        this.logger.debug("get file: ", file)
      };
      reader.readAsArrayBuffer(inputNode.files[0]);
    }
  }


}
