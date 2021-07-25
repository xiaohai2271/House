import {Injectable} from '@angular/core';
import {TodoTopicVO} from "../entity/viewobject/TodoTopicVO";
import {MenuItemInfo, MENU} from "./utils/Menu";
import {TodoItemVO} from "../entity/viewobject/TodoItemVO";
import {TodoItemApis, TopicApis} from "../../http/apis";
import {isTodoTopicVO} from "./utils/Types";
import {Observable, Observer} from "rxjs";
import {addListener, DATA_LISTENER_KEY, getListener, notifyListener} from "./utils/Listener";
import {TodoItemService} from "./todo-item.service";

@Injectable({
  providedIn: 'root'
})
export class TodoService {
  public static readonly LISTENER_KEY = {
    topic: 'topic',
    topicList: 'topicList',
  }

  constructor() {
    this.initTopicList();
    this.initMenuItemInfo();
    this.topic = this.menuItemInfos.all
    this.listenTodoItemChange();
  }

  public menuItemInfos: { [name: string]: MenuItemInfo } = {
    all: {menuInfo: MENU.all, show: true, icon: 'icon-all_noselects'},
    plan: {menuInfo: MENU.plan, show: true, icon: 'icon-calendar'},
    done: {menuInfo: MENU.done, show: true, icon: 'icon-done'},
    task: {menuInfo: MENU.task, show: true, icon: 'icon-plan'}
  }
  public topicList: TodoTopicVO[] = [];
  private _itemList: TodoItemVO[] = [];
  private _topic: TodoTopicVO | MenuItemInfo;
  public menuInfo: MenuItemInfo[] = [];

  initTopicList() {
    TopicApis.query().subscribe(obs => {
      this.topicList = obs.data;
      notifyListener(TodoService.LISTENER_KEY.topicList, f => {
        obs.code === 0 ? f.next(obs.data) : f.error(obs)
      })
    })
  }

  initMenuItemInfo() {
    this.menuInfo = [this.menuItemInfos.all, this.menuItemInfos.plan, this.menuItemInfos.done, this.menuItemInfos.task]
    this.menuItemInfos.all.items = this._itemList;
    this.menuItemInfos.plan.items = this._itemList.filter(it => it.deadlineDate)
    this.menuItemInfos.done.items = this._itemList.filter(it => it.done);
    this.menuItemInfos.task.items = this._itemList.filter(it => !it.done);
    this.menuInfo.forEach(menu => menu.title = menu.menuInfo.name)
  }


  calUnDoneCountSelf(): number {
    if (isTodoTopicVO(this.topic)) {
      const top: TodoTopicVO = this.topic;
      return this.calUnDoneCount(top, null)
    } else {
      return this.calUnDoneCount(null, this.topic)
    }
  }

  /**
   * 计算每个主题的未完成的代办事项数目
   * @param topic 待计算的主题
   * @param menuItem 待计算的菜单项
   */
  calUnDoneCount(topic: TodoTopicVO, menuItem: MenuItemInfo): number {
    const filterRule = topic => !topic.done;
    switch (menuItem?.menuInfo) {
      case MENU.all:
        return this._itemList.filter(filterRule).length || null
      case MENU.plan:
        return this._itemList.filter(top => top.deadlineDate).filter(filterRule).length || null
      case MENU.done:
        return null
      case MENU.task:
        return this._itemList.filter(filterRule).length || null
      default:
        return topic.items?.filter(filterRule).length || null
    }
  }

  set topic(topic: TodoTopicVO | MenuItemInfo) {
    this._topic = topic;
    if (isTodoTopicVO(topic)) {
      notifyListener(TodoService.LISTENER_KEY.topic, ons => ons.next(topic));
      topic.items = this._itemList?.filter(it => it.topic?.id == topic.id)
    } else {
      this.initMenuItemInfo();
      notifyListener(TodoService.LISTENER_KEY.topic, ons => ons.error(topic));
    }
  }

  get topic(): TodoTopicVO | MenuItemInfo {
    return this._topic;
  }

  listenTodoItemChange() {
    addListener<TodoItemVO[]>(TodoItemService.ITEM_LIST_KEY, {
      next: value => {
        this._itemList = value
        // 重新组织菜单数据 // ** 包含界面上的数据 **
        this.initMenuItemInfo();
      },
      error: e => null,
      complete: () => null
    })
  }
}
