import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {TodoComponent} from './todo.component';
import {RouterModule} from "@angular/router";
import {NzLayoutModule} from "ng-zorro-antd/layout";
import {NzMenuModule} from "ng-zorro-antd/menu";
import {IconsProviderModule} from "../../icons-provider.module";
import {NzDividerModule} from "ng-zorro-antd/divider";
import {TodoItemComponent} from "../component/todo-item/todo-item.component";
import {NzInputModule} from "ng-zorro-antd/input";
import {NzButtonModule} from "ng-zorro-antd/button";
import {NzPopoverModule} from "ng-zorro-antd/popover";
import {NzDatePickerModule} from "ng-zorro-antd/date-picker";
import {FormsModule} from "@angular/forms";
import {NzGridModule} from "ng-zorro-antd/grid";
import {NzNotificationService} from "ng-zorro-antd/notification";
import {NzBadgeModule} from "ng-zorro-antd/badge";


@NgModule({
  declarations: [TodoComponent, TodoItemComponent],
  imports: [
    CommonModule,
    RouterModule.forChild([
      {path: '', component: TodoComponent}
    ]),
    NzLayoutModule,
    NzMenuModule,
    IconsProviderModule,
    NzDividerModule,
    NzInputModule,
    NzButtonModule,
    NzPopoverModule,
    NzDatePickerModule,
    FormsModule,
    NzGridModule,
    NzBadgeModule,
  ],
  providers: [NzNotificationService]
})
export class TodoModule {
}
