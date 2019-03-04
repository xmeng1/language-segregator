import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {MatButtonModule, MatSelectModule} from '@angular/material';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatFileUploadModule} from 'angular-material-fileupload';
import {ApiModule, Configuration, ConfigurationParameters} from './segregator-ts-api';
import {HttpClientModule} from '@angular/common/http';

// configuring providers

// export function apiConfigFactory(): Configuration => {
//   const params: ConfigurationParameters = {
//     // set configuration parameters here.
//   }
//   return new Configuration(params);
// }

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MatSelectModule,
    MatButtonModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MatFileUploadModule,
    ApiModule,
    // make sure to import the HttpClientModule in the AppModule only,
    // see https://github.com/angular/angular/issues/20575
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
