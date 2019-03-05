import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent, AboutDialog} from './app.component';
import {MatButtonModule, MatChipsModule, MatSelectModule, MatIconModule} from '@angular/material';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatFileUploadModule} from 'angular-material-fileupload';
import {ApiModule, BASE_PATH, Configuration, ConfigurationParameters} from './segregator-ts-api';
import {HttpClientModule} from '@angular/common/http';
import {LoggerModule, NgxLoggerLevel} from 'ngx-logger';
import {MatAutocompleteModule} from '@angular/material/autocomplete';
import {MatExpansionModule} from '@angular/material/expansion';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatProgressBarModule} from '@angular/material/progress-bar';
import {MatCardModule} from '@angular/material/card';
import {MatDialogModule} from '@angular/material/dialog';
import {MatInputModule} from '@angular/material';
import {environment} from "../environments/environment";
// configuring providers

// export function apiConfigFactory(): Configuration => {
//   const params: ConfigurationParameters = {
//     // set configuration parameters here.
//   }
//   return new Configuration(params);
// }

@NgModule({
  declarations: [
    AppComponent,
    AboutDialog
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MatSelectModule,
    MatButtonModule,
    MatChipsModule,
    MatAutocompleteModule,
    MatIconModule,
    MatExpansionModule,
    MatGridListModule,
    MatProgressBarModule,
    MatCardModule,
    MatDialogModule,
    MatInputModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MatFileUploadModule,
    ApiModule,
    // make sure to import the HttpClientModule in the AppModule only,
    // see https://github.com/angular/angular/issues/20575
    HttpClientModule,
    LoggerModule.forRoot({
      // serverLoggingUrl: '/api/logs',
      level: NgxLoggerLevel.DEBUG,
      serverLogLevel: NgxLoggerLevel.ERROR
    })
  ],
  entryComponents: [AboutDialog],
  providers: [{ provide: BASE_PATH, useValue: environment.API_BASE_PATH }],
  bootstrap: [AppComponent]
})
export class AppModule {
}
