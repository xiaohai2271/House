import {Injectable} from '@angular/core';
import {TodoTopicVO} from "../entity/viewobject/TodoTopicVO";
import {MenuItemInfo, MENU} from "./utils/Menu";
import {TodoItemVO} from "../entity/viewobject/TodoItemVO";
import {TodoItemApis, TopicApis} from "../../http/apis";
import {isTodoTopicVO} from "./utils/Types";

@Injectable({
  providedIn: 'root'
})
export class TodoService {
  public sort = (a: TodoItemVO, b: TodoItemVO) => a.done ? -1 : (b.done ? 0 : 1);

  constructor() {
    TopicApis.query().subscribe(obs => {
      this.topicList = obs.data;
    })
    TodoItemApis.query().subscribe(obs => {
      this.itemList = obs.data;
      if (this.topicList == null) {
        setTimeout(this.setItemTopic, 1000)
      } else {
        this.setItemTopic();
      }
      this.initMenuItemInfo();
    })
    this.topic = this.menuItemInfos.all
  }

  public menuItemInfos: { [name: string]: MenuItemInfo } = {
    all: {menuInfo: MENU.all, show: true, icon: 'icon-all_noselects'},
    plan: {menuInfo: MENU.plan, show: true, icon: 'icon-calendar'},
    done: {menuInfo: MENU.done, show: true, icon: 'icon-done'},
    task: {menuInfo: MENU.task, show: true, icon: 'icon-plan'}
  }
  public topicList: TodoTopicVO[] = [];
  public itemList: TodoItemVO[] = [];
  public topic: TodoTopicVO | MenuItemInfo;
  public menuInfo: MenuItemInfo[] = [];


  initMenuItemInfo() {
    this.menuInfo = [this.menuItemInfos.all, this.menuItemInfos.plan, this.menuItemInfos.done, this.menuItemInfos.task]
    this.menuItemInfos.all.items = this.itemList;
    this.menuItemInfos.plan.items = this.itemList.filter(it => it.deadlineDate)
    this.menuItemInfos.done.items = this.itemList.filter(it => it.done);
    this.menuItemInfos.task.items = this.itemList.filter(it => !it.done);
    this.menuInfo.forEach(menu => menu.title = menu.menuInfo.name)
  }


  /**
   * 计算每个主题的未完成的代办事项数目
   * @param topic 待计算的主题
   * @param menuItem 待计算的菜单项
   */
  calUnDoneCount(topic: TodoTopicVO, menuItem: MenuItemInfo): number {
    const filterRule = topic => !topic.done;
    // if (!id) topic?.items?.filter(filterRule).length
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

  /**
   * 设置待办事项的主题
   */
  private setItemTopic = () => isTodoTopicVO(this.topic) ?
    this.topic.items.forEach(it => {
      let topicRes = this.topicList.filter(top => top.id == it.topicId)
      it.topic = topicRes ? topicRes[0] : null;
    })
    :
    null


}
