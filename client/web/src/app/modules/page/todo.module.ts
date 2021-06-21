import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {TodoComponent} from './todo.component';
import {RouterModule} from "@angular/router";
import {NzLayoutModule} from "ng-zorro-antd/layout";
import {NzMenuModule} from "ng-zorro-antd/menu";
import {IconsProviderModule} from "../../icons-provider.module";
import {NzDividerModule} from "ng-zorro-antd/divider";


@NgModule({
  declarations: [TodoComponent],
  imports: [
    CommonModule,
    RouterModule.forChild([
      {path: '', component: TodoComponent}
    ]),
    NzLayoutModule,
    NzMenuModule,
    IconsProviderModule,
    NzDividerModule,
  ],
})
export class TodoModule {
}
