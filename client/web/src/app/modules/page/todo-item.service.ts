import {Injectable} from '@angular/core';
import {MateInfo, Sortable} from "./utils/Types";
import {TodoItemVO} from "../entity/viewobject/TodoItemVO";
import {TodoTopicVO} from "../entity/viewobject/TodoTopicVO";
import {TodoService} from "./todo.service";
import {MenuItemInfo} from "./utils/Menu";
import {addListener, getListener, notifyListener} from "./utils/Listener";
import {Observer} from "rxjs";
import {TodoItemApis} from "../../http/apis";


@Injectable({
  providedIn: 'root'
})
export class TodoItemService {

  public static readonly LISTENER_KEY = "mateData";
  private itemList: TodoItemVO[] = [];
  public static readonly ITEM_LIST_KEY = "itemList";

  constructor() {
    this.initTodoItemData().then(r => this.initMateInfoMap());
    this.listenTopicChange()
  }

  public mateInfoMap = new Map<number, MateInfo<TodoItemVO>>();

  async initTodoItemData() {
    let response = await TodoItemApis.query().toPromise();
    this.itemList = response.data;
    notifyListener(TodoItemService.ITEM_LIST_KEY, ob => {
      ob.next(this.itemList)
    })
  }


  initMateInfoMap() {
    if (this.mateInfoMap.size > 0) return
    console.log("initMateInfoMap")
    let pre = null;
    this.itemList.forEach(it => {
      let result: MateInfo<TodoItemVO> = this.getOrDefault(it.id);
      result.data = it;
      result.topic.show = pre == undefined || it.topic?.id != pre.topic?.id
      this.setInfo(it.id, result)
      pre = it;
    })
  }

  // 隐藏 让界面不显示数据
  dealClassifyChange(item: TodoItemVO) {
    this.mateInfoMap.forEach((v, k) => {
      if (v.data?.topic?.id === item.topic?.id) {
        v.topic.expand = !v.topic.expand;
        this.notify(true, v)
      }
    })
  }

  getOrDefault(key: number) {
    if (!this.mateInfoMap.get(key)) {
      let result = {topic: {show: true, expand: true}, data: null};
      this.setInfo(key, result)
    }
    return this.mateInfoMap.get(key)
  }

  setInfo(key: number, item: MateInfo<TodoItemVO>) {
    this.mateInfoMap.set(key, item);
    getListener<MateInfo<TodoItemVO>>(TodoItemService.LISTENER_KEY).forEach(obs => obs.next(item))
  }

  // 添加数据监听器
  addListener = (observer: Observer<MateInfo<TodoItemVO>>) => addListener(TodoItemService.LISTENER_KEY, observer);

  notify = (needNotify: boolean, info: MateInfo<TodoItemVO>) => {
    if (needNotify) getListener<MateInfo<TodoItemVO>>(TodoItemService.LISTENER_KEY).forEach(obs => obs.next(info))
  }

  listenTopicChange() {
    addListener<TodoTopicVO>(TodoService.LISTENER_KEY.topic, {
      next: top => {
        this.initMateInfoMap();
        this.mateInfoMap.forEach((v, k) => {
          let needNotify = false;
          if (v.topic.show) {
            v.topic.show = false;
            needNotify = true;
          }
          if (!v.topic.expand) {
            v.topic.expand = true;
            needNotify = true;
          }
          this.notify(needNotify, v)
        })
      },
      error: (menu: MenuItemInfo) => {
        this.initMateInfoMap();
        let pre = null;
        menu.items.forEach(it => {
          let info = this.getOrDefault(it.id)
          let preShow = info.topic.show;
          let needNotify = false;
          info.topic.show = pre == undefined || it.topic?.id != pre.topic?.id
          needNotify = preShow != info.topic.show;
          needNotify = needNotify || !info.topic.expand
          info.topic.expand = true;
          this.notify(needNotify, info)
          pre = it;
        })
      },
      complete: () => null
    })
  }
}
