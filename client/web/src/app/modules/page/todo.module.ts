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
import {NzPopconfirmModule} from "ng-zorro-antd/popconfirm";
import {NzModalModule} from "ng-zorro-antd/modal";
import {NzDrawerModule} from "ng-zorro-antd/drawer";
import {ItemDetailComponent} from './components/item-detail/item-detail.component';
import {UserInfoComponent} from './components/user-info/user-info.component';
import {TopicListComponent} from './components/topic-list/topic-list.component';
import {NewTopicComponent} from './components/new-topic/new-topic.component';
import {TodoService} from "./todo.service";
import {NzDropDownModule} from "ng-zorro-antd/dropdown";
import {StatisticsComponent} from './components/statistics/statistics.component';
import {ClockInComponent} from './components/clock-in/clock-in.component';
import {NzCalendarModule} from "ng-zorro-antd/calendar";
import {NewTodoComponent} from './components/new-todo/new-todo.component';
import {NzSelectModule} from "ng-zorro-antd/select";
import {NzTagModule} from "ng-zorro-antd/tag";


@NgModule({
  declarations: [TodoComponent, TodoItemComponent, ItemDetailComponent, UserInfoComponent, TopicListComponent, NewTopicComponent, StatisticsComponent, ClockInComponent, NewTodoComponent],
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
    NzPopconfirmModule,
    NzModalModule,
    NzDrawerModule,
    NzDropDownModule,
    NzCalendarModule,
    NzSelectModule,
    NzTagModule,
  ],
  providers: [
    NzNotificationService,
    TodoService
  ]
})
export class TodoModule {
}
