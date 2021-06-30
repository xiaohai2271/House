import {Component, OnInit} from '@angular/core';
import {NzIconService} from "ng-zorro-antd/icon";
import {TodoItemApis, TopicApis} from "../../http/apis";
import {TodoItemVO} from "../entity/viewobject/TodoItemVO";
import {TodoItem} from "../entity/request/TodoItem";
import {NzNotificationService} from "ng-zorro-antd/notification";
import {DrawerData} from "./components/item-detail/item-detail.component";
import {TodoTopic} from "../entity/request/TodoTopic";
import {TodoService} from "./todo.service";
import {TodoTopicVO} from "../entity/viewobject/TodoTopicVO";

@Component({
  selector: 'app-todo',
  templateUrl: './todo.component.html',
  styleUrls: ['./todo.component.less'],
})
export class TodoComponent implements OnInit {
  constructor(private iconService: NzIconService,
              private notification: NzNotificationService,
              public todoService: TodoService
  ) {
    this.iconService.fetchFromIconfont({
      scriptUrl: '//at.alicdn.com/t/font_2623130_kyveddl18yg.js'
    });
  }

  public inputStatus: boolean = false;

  drawer: DrawerData = {
    visible: false,
    data: null,
    dataChanged: false,
    close: () => {
      this.drawer.visible = false;
      if (!this.drawer.dataChanged) return
      const reqData: TodoItem = {
        completeDate: this.drawer.data?.completeDate ? new Date(this.drawer.data?.completeDate) : null,
        createDate: this.drawer.data?.createDate ? new Date(this.drawer.data?.createDate) : null,
        deadlineDate: this.drawer.data?.deadlineDate ? new Date(this.drawer.data?.deadlineDate) : null,
        description: this.drawer.data?.description,
        id: this.drawer.data?.id,
        title: this.drawer.data?.title,
        topicId: this.drawer.data?.topicId,
        done: this.drawer.data?.done
      }
      TodoItemApis.update(reqData)
    },
    deleteItem: (data: TodoItemVO) => this.deleteItem(data)
  };

  ngOnInit(): void {
  }

  itemStatusChange(status: boolean) {
    console.log(status)
  }

  // check2Input(status: boolean, e?: HTMLInputElement) {
  //   this.inputStatus = status;
  //   this.dataTimePickerVisible = false;
  //   e?.focus()
  // }

  /**
   * 新建待办事项
   */
  submitInfo() {
    // if (this.addTaskData.title.length == 0) {
    //   return
    // }
    // TodoItemApis.create(this.addTaskData).subscribe(obs => {
    //   if (obs.code == 0) {
    //     this.notification.blank(
    //       '创建成功😊',
    //       `创建成功${(this.addTaskData.deadlineDate ? `,请在${formatDate(this.addTaskData.deadlineDate, 'yyyy-MM-dd HH:mm:ss', "zh_CN")}之前完成哦 :) ` : '')}`,
    //     );
    //   }
    // })
  }

  deleteItem(data: TodoItemVO) {
    TodoItemApis.deleteOne(data.id).subscribe(data => {
      if (data.code == 0) {
        this.notification.blank(
          '删除成功😊',
          ''
        );
      } else {
        this.notification.blank(
          '删除失败😣',
          data.msg
        );
      }
    })
    return false
  }

  showItemDetail(data: TodoItemVO) {
    this.drawer.visible = true;
    this.drawer.data = data;
  }

  createTopic(e: TodoTopic) {
    TopicApis.create(e).subscribe(obs => {
      if (obs.code == 0) {
        this.notification.blank(
          '创建成功😊',
          ''
        );
      }
    })
  }

  deleteTopic(topicVO: TodoTopicVO) {
    TopicApis.deleteOne(topicVO.id).subscribe(obs => {
      if (obs.code == 0) {
        this.notification.blank(
          '删除成功😊',
          ''
        );
      }
    })
  }

  updateTopic(topicVO: TodoTopicVO) {
    TopicApis.update({
      date: new Date(topicVO.date),
      userId: topicVO.userId,
      color: topicVO.color,
      icon: topicVO.icon,
      id: topicVO.id,
      title: topicVO.title
    }).subscribe(obs => {
      if (obs.code == 0) {
        this.notification.blank(
          '更新成功😊',
          ''
        );
      }
    })
  }
}
