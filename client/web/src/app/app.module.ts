import {LOCALE_ID, NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {NZ_I18N} from 'ng-zorro-antd/i18n';
import {zh_CN} from 'ng-zorro-antd/i18n';
import {DatePipe, registerLocaleData} from '@angular/common';
import zh from '@angular/common/locales/zh';
import {FormsModule} from '@angular/forms';
import {HTTP_INTERCEPTORS, HttpClient, HttpClientModule} from '@angular/common/http';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {IconsProviderModule} from './icons-provider.module';
import {NzLayoutModule} from 'ng-zorro-antd/layout';
import {NzMenuModule} from 'ng-zorro-antd/menu';
import {TodoModule} from "./modules/page/todo.module";
import {setHttpClient} from "./http/apis"
import {ApiHttpInterceptor} from "./http/api-http.interceptor";
import {TodoService} from "./modules/page/todo.service";
import {TodoItemService} from "./modules/page/components/todo-item/todo-item.service";

registerLocaleData(zh);

@NgModule({
  declarations: [AppComponent,],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    IconsProviderModule,
    NzLayoutModule,
    NzMenuModule,
    TodoModule
  ],
  providers: [
    {provide: NZ_I18N, useValue: zh_CN},
    {provide: HTTP_INTERCEPTORS, useClass: ApiHttpInterceptor, multi: true},
    {provide: LOCALE_ID, useValue: 'zh'}
  ],
  bootstrap: [AppComponent],
})
export class AppModule {

  constructor(httpClient: HttpClient) {
    setHttpClient(httpClient)
  }
}
