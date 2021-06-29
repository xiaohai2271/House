import {Component, OnInit, ViewChild} from '@angular/core';
import {NzIconService} from "ng-zorro-antd/icon";
import {TodoItemApis, TopicApis} from "../../http/apis";
import {TodoTopicVO} from "../entity/viewobject/TodoTopicVO";
import {TodoItemVO} from "../entity/viewobject/TodoItemVO";
import {TodoItem} from "../entity/request/TodoItem";
import {NzNotificationService} from "ng-zorro-antd/notification";
import {formatDate} from "@angular/common";
import {TodoTopic} from "../entity/request/TodoTopic";
import {any} from "codelyzer/util/function";


declare interface MenuItemInfo {
  menuInfo: MenuID
  icon: string,
  show: boolean
}

declare interface MenuID {
  id: number,
  name: string
}

export const MENU: { [name: string]: MenuID } = {
  all: {id: 1, name: '全部'},
  plan: {id: 2, name: '计划'},
  done: {id: 3, name: '已完成'},
  task: {id: 4, name: '任务'}
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
      scriptUrl: '//at.alicdn.com/t/font_2623130_kyveddl18yg.js'
    });

    this.menuInfo = [
      {menuInfo: MENU.all, show: true, icon: 'icon-all_noselects'},
      {menuInfo: MENU.plan, show: true, icon: 'icon-calendar'},
      {menuInfo: MENU.done, show: true, icon: 'icon-done'},
      {menuInfo: MENU.task, show: true, icon: 'icon-plan'},
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
  addTopicTipVisible: boolean = true;
  drawer: { visible: boolean, data?: TodoItemVO } = {
    visible: false,
    data: null
  };

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

  setTopic(topic: TodoTopicVO, id?: number) {

    if (!id) {
      this.topic = topic;
      this.addTaskData.topicId = topic.id;
      this.topic.items?.sort(this.sort)
      return
    }
    this.topic = {
      title: '',
      items: [],
      ...this.topic
    }
    switch (id) {
      case MENU.all.id:
        this.topic.title = '全部';
        this.topic.items = this.itemList;
        break;
      case MENU.plan.id:
        this.topic.title = '计划';
        this.topic.items = this.itemList.filter(top => top.deadlineDate);
        break;
      case MENU.done.id:
        this.topic.title = '已完成';
        this.topic.items = this.itemList.filter(top => top.done);
        break;
      case MENU.task.id:
        this.topic.title = '任务';
        this.topic.items = this.itemList.filter(top => !top.done);
        break;
      default:
        break
    }
    this.topic.items?.sort(this.sort)
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

  showAddTopic(AddTopic: HTMLInputElement) {
    this.addTopicTipVisible = false;
    setTimeout(() => AddTopic.focus(), 100)
  }

  submitTopicInfo(e: InputEvent) {
    console.log((e.target as HTMLInputElement).value)
    TopicApis.create({
      title: (e.target as HTMLInputElement).value,
      date: new Date(),
      userId: 1
    }).subscribe(obs => {
      if (obs.code == 0) {
        this.notification.blank(
          '创建成功😊',
          ''
        );
        (e.target as HTMLInputElement).value = null;
        this.addTopicTipVisible = true;
        this.topicList.push(obs.data)
      }
    })
  }

  calUnDoneCount(topic: TodoTopicVO, id: number): number {
    const filterRule = topic => !topic.done;
    // if (!id) topic?.items?.filter(filterRule).length
    switch (id) {
      case MENU.all.id:
        return this.itemList.filter(filterRule).length || null
      case MENU.plan.id:
        return this.itemList.filter(top => top.deadlineDate).filter(filterRule).length || null
      case MENU.done.id:
        return null
      case MENU.task.id:
        return this.itemList.filter(filterRule).length || null
      default:
        return topic.items?.filter(filterRule).length || null
    }
  }

  deleteItem(data: TodoItemVO) {
    console.log("delete", data)
  }

  showItemDetail(data: TodoItemVO) {
    this.drawer.visible = true;
    this.drawer.data = data;
  }
}
