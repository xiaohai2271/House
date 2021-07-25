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
import {clearTime, isEqual} from "./utils/Date";
import {formatDate} from "@angular/common";
import {isTodoTopicVO, MateInfo, ModalData} from "./utils/Types";
import {TodoItemService} from "./todo-item.service";
import {createModalData, copyOf} from "./utils/Object";

@Component({
  selector: 'app-todo',
  templateUrl: './todo.component.html',
  styleUrls: ['./todo.component.less'],
})
export class TodoComponent implements OnInit {
  constructor(private iconService: NzIconService,
              private notification: NzNotificationService,
              public todoService: TodoService,
              public todoItemService: TodoItemService
  ) {
    this.iconService.fetchFromIconfont({
      scriptUrl: '//at.alicdn.com/t/font_2623130_a87m0sfgj2.js'
    });
  }

  /**
   * 新增待办事项的弹窗数据集
   */
  addTaskData: ModalData<void> = createModalData({
    visible: false,
    onOk: (todoItem: TodoItem) => {
      todoItem.topicId = isTodoTopicVO(this.todoService.topic) ? this.todoService.topic.id : null
      TodoItemApis.create(todoItem).subscribe(obs => {
        if (obs.code == 0) {
          this.notification.blank(
            '创建成功😊',
            todoItem.deadlineDate ?
              `记得在${formatDate(todoItem.deadlineDate, "yyy/MM/dd HH:mm, EEEE", 'zh')}之前完成哦！`
              : ''
          );
        }
      })
      this.addTaskData.onClose();
    }
  })
  /**
   * 侧滑窗体数据集
   */
  drawer: DrawerData = {
    visible: false,
    editable: false,
    data: null,
    checkDataChange: (oldVal: TodoItemVO, newVal: TodoItemVO) => {
      return this.drawer.editable && (
        oldVal.topic?.id != newVal.topic?.id ||
        oldVal.deadlineDate != newVal.deadlineDate ||
        oldVal.description != newVal.description
      )
    },
    close: () => {
      this.drawer.visible = false;
      if (!this.drawer.checkDataChange(this.oldTodoItemData, this.drawer.data)) return
      const reqData: TodoItem = {
        completeDate: this.drawer.data?.completeDate ? new Date(this.drawer.data?.completeDate) : null,
        createDate: this.drawer.data?.createDate ? new Date(this.drawer.data?.createDate) : null,
        deadlineDate: this.drawer.data?.deadlineDate ? new Date(this.drawer.data?.deadlineDate) : null,
        description: this.drawer.data?.description,
        id: this.drawer.data?.id,
        title: this.drawer.data?.title,
        topicId: this.drawer.data?.topic.id,
        done: this.drawer.data?.done
      }
      TodoItemApis.update(reqData).subscribe(obs => {
        if (obs.code == 0) {
          this.notification.blank(
            '更新成功😊',
            ''
          );
        } else {
          this.notification.blank(
            '更新失败😣',
            obs.msg
          );
        }
      })
    },
    deleteItem: (data: TodoItemVO) => this.deleteItem(data)
  };

  private oldTodoItemData: TodoItemVO;

  ngOnInit(): void {
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

  showItemDetail(data: TodoItemVO, editMode: boolean = false) {
    this.drawer.visible = true;
    this.drawer.data = data;
    this.oldTodoItemData = copyOf(data)
    this.drawer.editable = editMode;
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

  dateChanged(date: Date) {
    const dateItem = this.todoService.topic?.items?.filter(top => isEqual(clearTime(new Date(top.createDate)), clearTime(date)));
    console.log(date, dateItem)
  }

  updateTodoItem(todoItem: TodoItem) {
    console.log(todoItem)
    TodoItemApis.update(todoItem).subscribe(obs => {
      if (obs.code == 0) {
        this.notification.blank(
          '更新成功😊',
          ''
        );
      }
    })
  }

  getClassifyMateInfo(item: TodoItemVO): MateInfo<TodoItemVO> {
    return this.todoItemService.mateInfo.getOrDefault(item.id);
  }

  expandInfoChange(item: TodoItemVO, $event: boolean) {
    this.todoItemService.mateInfo.mateInfoMap.forEach((v, k) => {
      if (v.data?.topic?.id === item.topic?.id) {
        v.topic.expand = $event;
      }
    })
  }
}
