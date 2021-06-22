import {Component, OnInit, ViewChild} from '@angular/core';
import {NzIconService} from "ng-zorro-antd/icon";
import {TodoItemApis, TopicApis} from "../../http/apis";
import {TodoTopicVO} from "../entity/viewobject/TodoTopicVO";
import {TodoItemVO} from "../entity/viewobject/TodoItemVO";
import {TodoItem} from "../entity/request/TodoItem";
import {NzNotificationService} from "ng-zorro-antd/notification";
import {formatDate} from "@angular/common";


declare interface MenuItemInfo {
  name: string,
  id: number,
  icon: string,
  show: boolean
}

@Component({
  selector: 'app-todo',
  templateUrl: './todo.component.html',
  styleUrls: ['./todo.component.less'],
})
export class TodoComponent implements OnInit {
  constructor(private iconService: NzIconService,
              private notification: NzNotificationService,
  ) {
    this.iconService.fetchFromIconfont({
      scriptUrl: '//at.alicdn.com/t/font_2623130_4tankwkjxet.js'
    });

    this.menuInfo = [
      {name: '全部', id: 1, show: true, icon: 'icon-all_noselects'},
      {name: '计划', id: 2, show: true, icon: 'icon-calendar'},
      {name: '已完成', id: 3, show: true, icon: 'icon-done'},
      {name: '任务', id: 4, show: true, icon: 'icon-plan'},
    ]
  }

  public menuInfo: MenuItemInfo[] = [];
  public topicList: TodoTopicVO[] = [];
  public itemList: TodoItemVO[] = [];
  public topic: TodoTopicVO;
  public sort = (a: TodoItemVO, b: TodoItemVO) => a.done ? -1 : (b.done ? 0 : 1);
  public inputStatus: boolean = false;

  public addTaskData: TodoItem = {createDate: new Date(), title: ""};
  dataTimePickerVisible: boolean;

  ngOnInit(): void {
    TopicApis.query().subscribe(obs => {
      this.topicList = obs.data;
    })
    TodoItemApis.query().subscribe(obs => {
      this.itemList = obs.data;
      this.topic.items = obs.data;

      this.topic.items.sort(this.sort)
    })
    this.topic = {color: "", date: "", icon: "", id: 0, items: [], title: "全部", userId: 0}
  }

  setTopic(topic: TodoTopicVO, id: number) {
    switch (id) {
      case 1:
        this.topic.title = '全部';
        this.topic.items = this.itemList;
        break;
      case 2:
        this.topic.title = '计划';
        this.topic.items = this.itemList.filter(top => top.deadlineDate);
        break;
      case 3:
        this.topic.title = '已完成';
        this.topic.items = this.itemList.filter(top => top.done);
        break;
      case 4:
        this.topic.title = '任务';
        this.topic.items = this.itemList.filter(top => !top.done);
        break;
      default:
        this.topic = topic;
        this.addTaskData.topicId = topic.id;
        break
    }
    this.topic.items.sort(this.sort)
  }


  itemStatusChange(status: boolean) {
    console.log(status)
  }

  check2Input(status: boolean, e?: HTMLInputElement) {
    this.inputStatus = status;
    this.dataTimePickerVisible = false;
    e?.focus()
  }

  submitInfo() {
    if (this.addTaskData.title.length == 0) {
      return
    }
    TodoItemApis.create(this.addTaskData).subscribe(obs => {
      if (obs.code == 0) {
        this.notification.blank(
          '创建成功😊',
          `创建成功${(this.addTaskData.deadlineDate ? `,请在${formatDate(this.addTaskData.deadlineDate, 'yyyy-MM-dd HH:mm:ss', "zh_CN")}之前完成哦 :) ` : '')}`,
        );
      }
    })
  }
}