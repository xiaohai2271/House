import {Injectable} from '@angular/core';
import {TodoTopicVO} from "../entity/viewobject/TodoTopicVO";
import {MenuItemInfo, MENU} from "./utils/Menu";
import {TodoItemVO} from "../entity/viewobject/TodoItemVO";
import {TodoItemApis, TopicApis} from "../../http/apis";
import {isTodoTopicVO} from "./utils/Types";
import {Observable, Observer} from "rxjs";
import {addListener, DATA_LISTENER_KEY, getListener} from "./utils/Listener";

@Injectable({
  providedIn: 'root'
})
export class TodoService {
  public readonly LISTENER_KEY = {
    topic: 'topic',
    topicList: 'topicList',
    todoItemList: "itemList"
  }

  constructor() {
    TopicApis.query().subscribe(obs => {
      this.topicList = obs.data;
    })
    TodoItemApis.query().subscribe(obs => {
      this.itemList = obs.data;
      this.initMenuItemInfo();
    })
    this.topic = this.menuItemInfos.all
    new Observable<TodoTopicVO>(obs => {
      getListener(this.LISTENER_KEY.topic).push(obs)
    }).subscribe(obs => obs.items = this.itemList?.filter(it => it.topic?.id == obs.id), error => null)
  }

  public menuItemInfos: { [name: string]: MenuItemInfo } = {
    all: {menuInfo: MENU.all, show: true, icon: 'icon-all_noselects'},
    plan: {menuInfo: MENU.plan, show: true, icon: 'icon-calendar'},
    done: {menuInfo: MENU.done, show: true, icon: 'icon-done'},
    task: {menuInfo: MENU.task, show: true, icon: 'icon-plan'}
  }
  public topicList: TodoTopicVO[] = [];
  public itemList: TodoItemVO[] = [];
  private _topic: TodoTopicVO | MenuItemInfo;
  private _listeners = new Map<string, Observer<any>[]>()
  public menuInfo: MenuItemInfo[] = [];


  initMenuItemInfo() {
    this.menuInfo = [this.menuItemInfos.all, this.menuItemInfos.plan, this.menuItemInfos.done, this.menuItemInfos.task]
    this.menuItemInfos.all.items = this.itemList;
    this.menuItemInfos.plan.items = this.itemList.filter(it => it.deadlineDate)
    this.menuItemInfos.done.items = this.itemList.filter(it => it.done);
    this.menuItemInfos.task.items = this.itemList.filter(it => !it.done);
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
        return this.itemList.filter(filterRule).length || null
      case MENU.plan:
        return this.itemList.filter(top => top.deadlineDate).filter(filterRule).length || null
      case MENU.done:
        return null
      case MENU.task:
        return this.itemList.filter(filterRule).length || null
      default:
        return topic.items?.filter(filterRule).length || null
    }
  }

  set topic(topic: TodoTopicVO | MenuItemInfo) {
    this._topic = topic;
    if (isTodoTopicVO(topic))
      getListener(this.LISTENER_KEY.topic).forEach(ons => ons.next(topic));
    else {
      this.initMenuItemInfo();
      getListener(this.LISTENER_KEY.topic).forEach(ons => ons.error(topic));
    }
  }

  get topic(): TodoTopicVO | MenuItemInfo {
    return this._topic;
  }

  addDataListener = <T>(key: DATA_LISTENER_KEY, observer: Observer<T>) => addListener<T>(key, observer);
}
