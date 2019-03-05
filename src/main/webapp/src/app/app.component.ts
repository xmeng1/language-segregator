import {Component, OnChanges, OnInit, SimpleChanges, ViewChild} from '@angular/core';
import {FormControl} from '@angular/forms';
import {
  DocOptions,
  ResultGitVersion, ResultListSegItem,
  SegregatorControllerService,
  VersionControllerService
} from './segregator-ts-api';
import {NGXLogger} from "ngx-logger";
import localeCode from "iso-639-1";
import {Observable} from "rxjs";
import {COMMA, ENTER} from '@angular/cdk/keycodes';
import {ElementRef} from '@angular/core';
import {MatAutocompleteSelectedEvent, MatChipInputEvent, MatAutocomplete} from '@angular/material';
import {map, startWith} from 'rxjs/operators';
import {MatSelectChange} from "@angular/material/typings/esm5/select";
import {Title} from "@angular/platform-browser";
import {HttpEvent, HttpEventType} from "@angular/common/http";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

  /*
  branch: "develop"
​​​​
buildTime: "2019-03-04T07:34:13+0000"
​​
buildVersion: "0.0.1"
​​
closestTagCommitCount: ""
​​
closestTagName: ""
​​
commitId: "06195d9a79883491a99332a96886a5322c78dfb2"
​​
commitIdAbbrev: "06195d9"
​​
commitIdDescribe: "06195d9-dirty"
​​
commitIdDescribeShort: "06195d9-dirty"
​​
commitMessageFull: "basic material design work"
​​
commitMessageShort: "basic material design work"
​​
commitTime: "2019-03-04T07:10:16+0000"
​​​​
dirty: "true"
​​​​
tags: ""
​​
totalCommitCount: "29"
  * */
  version = '';
  commitIdAbbrev = '';
  commitTime = '';
  buildTime = '';
  constructor(private versionControllerService: VersionControllerService,
              private segregatorControllerService: SegregatorControllerService,
              private logger: NGXLogger) {
    versionControllerService.getVersionUsingGET().subscribe((res: ResultGitVersion) => {
      this.logger.debug("get the version result", res);
      this.version = res.result['buildVersion'];
      this.commitIdAbbrev = res.result['commitIdAbbrev'];
      this.commitTime = res.result['commitTime'];
      this.buildTime = res.result['buildTime'];
    });
  }

  title = 'language-segregator';
  langSelectFormControl = new FormControl();

  allLangList: string[] = [];
  descToLang: { [key: string]: string; } = {};

  getDecsFromLangCode(n: string): string {
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
      description = "unknown";
    }
    return description;
  }

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
      this.allLangList.push(description);
      this.descToLang[description] = n;
    }
    this.allLangList.sort();
    this.logger.debug(localeCode.getName('en'));
    // this.logger.debug(this.allLangList)

  }

  selectionChange(selectedData: MatSelectChange) {
    this.logger.debug("selectionChange ", selectedData);
    this.selectedLang = [];
    let data = selectedData.value as Array<string>;
    data.forEach((element: string) => {
      this.selectedLang.push(element)
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
    // this.langSelectFormControl.reset()
    // this.langSelectFormControl.value = this.selectedLang;
  }
  selectedFile: string = "";
  fileEvent(fileInput: Event){
    this.logger.debug("file event: ", fileInput);
    let traget: any = fileInput.target;
    let file = traget.files[0];
    this.selectedFile = file.name;
  }
  onFileSelected() {
    const inputNode: any = document.querySelector('#file');

    if (typeof (FileReader) !== 'undefined') {
      const reader = new FileReader();

      reader.onload = (file: any) => {
        this.logger.debug("get file: ", file)
        this.file = file;
      };
      reader.readAsArrayBuffer(inputNode.files[0]);
    }
  }

  file: any; // FileReader

  analysis() {
    // get the selected languages
    this.logger.debug("get the selected languages ", this.selectedLang);
    let lang: Array<DocOptions.LangListEnum> = [];
    let langCode;
    this.selectedLang.forEach((x: string) => {
      langCode = this.descToLang[x];
      let langEnum: DocOptions.LangListEnum = DocOptions.LangListEnum[langCode];
      lang.push(langEnum);
      this.logger.debug("convert to language code: ", langCode);
    });
    // get the blob form the file
    this.logger.debug("get the file: ", this.file);
    let blob = new Blob([this.file.target.result]);
    // this.file.onload((e: Event & { target: { result: string } }) => {
    //   this.logger.debug("get the blob form the file: ", target.result);
    // })
    this.logger.debug("get the blob form the file: ", blob);
    /*
        splitDocUsingPOST(
        doc: Blob,
        docOptionsBlockSeparator?: 'BLANK_LINE' | 'LINE_BREAK' | 'REGEX_EXPRESS',
        docOptionsBlockSeparatorRex?: string,
        docOptionsLangList?: Array<'UNKNOWN' | 'AF' | 'AN' | 'AR' | 'BE' | 'BR' | 'CA' | 'BG' | 'BN' | 'CS' | 'CY' | 'DA' | 'DE' | 'EL' | 'EN' | 'ES' | 'ET' | 'EU' | 'FA' | 'FI' | 'FR' | 'GA' | 'GL' | 'GU' | 'HE' | 'HI' | 'HR' | 'HT' | 'HU' | 'ID' | 'IS' | 'IT' | 'JA' | 'KM' | 'KN' | 'KO' | 'LT' | 'LV' | 'MK' | 'ML' | 'MR' | 'MS' | 'MT' | 'NE' | 'NL' | 'NO' | 'OC' | 'PA' | 'PL' | 'PT' | 'RO' | 'RU' | 'SK' | 'SL' | 'SO' | 'SQ' | 'SR' | 'SV' | 'SW' | 'TA' | 'TE' | 'TH' | 'TL' | 'TR' | 'UK' | 'UR' | 'VI' | 'WA' | 'YI' | 'AST' | 'ZH_CN' | 'ZH_TW'>,
        docOptionsSupportMapping?: boolean,
        docOptionsSupportTitle?: boolean,
        source?: string,
        titlePatternOptionsAllUpperCase?: boolean,
        titlePatternOptionsFilterByCase?: boolean,
        titlePatternOptionsFilterByLang?: boolean,
        titlePatternOptionsFilterByLength?: boolean,
        titlePatternOptionsFilterByRegex?: boolean,
        titlePatternOptionsLang?: 'UNKNOWN' | 'AF' | 'AN' | 'AR' | 'BE' | 'BR' | 'CA' | 'BG' | 'BN' | 'CS' | 'CY' | 'DA' | 'DE' | 'EL' | 'EN' | 'ES' | 'ET' | 'EU' | 'FA' | 'FI' | 'FR' | 'GA' | 'GL' | 'GU' | 'HE' | 'HI' | 'HR' | 'HT' | 'HU' | 'ID' | 'IS' | 'IT' | 'JA' | 'KM' | 'KN' | 'KO' | 'LT' | 'LV' | 'MK' | 'ML' | 'MR' | 'MS' | 'MT' | 'NE' | 'NL' | 'NO' | 'OC' | 'PA' | 'PL' | 'PT' | 'RO' | 'RU' | 'SK' | 'SL' | 'SO' | 'SQ' | 'SR' | 'SV' | 'SW' | 'TA' | 'TE' | 'TH' | 'TL' | 'TR' | 'UK' | 'UR' | 'VI' | 'WA' | 'YI' | 'AST' | 'ZH_CN' | 'ZH_TW',
        titlePatternOptionsLengthThreshold?: number,
        titlePatternOptionsRegexPattern?: string,
        observe: any = 'body',
        reportProgress: boolean = false ): Observable<any> {
    */
    // invoke the api
    this.showSpinner = true;
    this.segregatorControllerService.splitDocUsingPOST(blob, undefined,
      undefined, lang, undefined,
      undefined, undefined, undefined,
      undefined, undefined, undefined,
      undefined, undefined,
      undefined,undefined, undefined, true).subscribe(
      res => {
        this.showSpinner = false;
        this.logger.debug("get the split result: ", res);
        let sum: number = Object.keys(res.list[0].contents).length + Object.keys(res.list[0].titles).length;
        this.logger.debug("the sum of content and titles", sum);
        this.logger.debug("result", res.list[0].titles);
        this.logger.debug("result", res.list[0].contents);
        this.logger.debug("result", res.list[1].titles);
        this.logger.debug("result", res.list[1].contents);
        this.tiles.push({
          text: this.getDecsFromLangCode(res.list[0].language.toString()),
          cols: 2,
          rows: 1,
          color: 'lightgreen'
        });
        this.tiles.push({
          text: this.getDecsFromLangCode(res.list[1].language.toString()),
          cols: 2,
          rows: 1,
          color: 'lightpink'
        });
        for (let _i = 1; _i <= sum; _i++) {
          let key: string = _i.toString();

          if (res.list[0].titles.hasOwnProperty(key)) {
            this.tiles.push({text: res.list[0].titles[key], cols: 4, rows: 1, color: 'lightblue', height: 50});
          } else {
            this.tiles.push({text: res.list[0].contents[key], cols: 2, rows: 1, color: 'lightgreen'});
            this.tiles.push({text: res.list[1].contents[key], cols: 2, rows: 1, color: 'lightpink'});
          }
        }
      }, (event: HttpEvent<ResultListSegItem>) => {
        console.log(event);
        switch (event.type) {
          case HttpEventType.Sent:
            this.showSpinner = true;
            console.log('Request sent!');
            break;
          case HttpEventType.ResponseHeader:
            console.log('Response header received!');
            break;
          case HttpEventType.UploadProgress:
            const percentDone = Math.round(100 * event.loaded / event.total);
            console.log(`File is ${percentDone}% uploaded.`);
          case HttpEventType.DownloadProgress:
            const kbLoaded = Math.round(event.loaded / 1024);
            console.log(`Download in progress! ${kbLoaded}Kb loaded`);
            break;
          case HttpEventType.Response:
            console.log('😺 Done!', event.body);
            this.showSpinner = false;
        }
      }
    )
  }

  showSpinner = false;
  tiles: Tile[] = [
    // {text: 'One', cols: 4, rows: 1, color: 'lightblue'},
    // {text: 'Two', cols: 2, rows: 1, color: 'lightgreen'},
    // {text: 'Three', cols: 2, rows: 1, color: 'lightpink'},
    // {text: 'Four', cols: 4, rows: 1, color: '#DDBDF1'},
  ];
}

export interface Tile {
  color: string;
  cols: number;
  rows: number;
  text: string;
  height?: number;
}
