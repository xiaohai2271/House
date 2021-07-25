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
    this.initTodoItemData().then(r => this.mateInfo.initMateInfoMap());
    this.listenTopicChange()
  }

  public mateInfo = {
    mateInfoMap: new Map<number, MateInfo<TodoItemVO>>(),
    initMateInfoMap: () => {
      if (this.mateInfo.mateInfoMap.size > 0) return
      this.itemList.forEach(it => {
        let result: MateInfo<TodoItemVO> = this.mateInfo.getOrDefault(it.id);
        result.data = it;
        result.title = it.topic?.title
        //result.topic.show = pre == undefined || it.topic?.id != pre.topic?.id
        result.topic.show = this.mateInfo.isHeadOfList(it, this.itemList.filter(itt => itt.topic?.id === it.topic?.id), null)
        this.mateInfo.setInfo(it.id, result)
      })
    },
    // 隐藏 让界面不显示数据
    dealClassifyChange: (item: TodoItemVO) => {
      this.mateInfo.mateInfoMap.forEach((v, k) => {
        if (v.data?.topic?.id === item.topic?.id) {
          v.topic.expand = !v.topic.expand;
          this.notify(v)
        }
      })
    },
    getOrDefault: (key: number) => {
      if (!this.mateInfo.mateInfoMap.get(key)) {
        let result = {topic: {show: true, expand: true}, data: null, title: null};
        this.mateInfo.setInfo(key, result)
      }
      return this.mateInfo.mateInfoMap.get(key)
    },
    setInfo: (key: number, item: MateInfo<TodoItemVO>) => {
      this.mateInfo.mateInfoMap.set(key, item);
      getListener<MateInfo<TodoItemVO>>(TodoItemService.LISTENER_KEY).forEach(obs => obs.next(item))
    },
    isHeadOfList: (it: TodoItemVO, list: TodoItemVO[], sortFn: Sortable) => {
      if (sortFn) list.sort(sortFn)
      return list.length > 0 && list[0].id === it.id;
    }
  }

  async initTodoItemData() {
    let response = await TodoItemApis.query().toPromise();
    this.itemList = response.data;
    notifyListener(TodoItemService.ITEM_LIST_KEY, ob => {
      ob.next(this.itemList)
    })
  }

  // 添加数据监听器
  addListener = (observer: Observer<MateInfo<TodoItemVO>>) => addListener(TodoItemService.LISTENER_KEY, observer);

  notify = (info: MateInfo<TodoItemVO>) => {
    getListener<MateInfo<TodoItemVO>>(TodoItemService.LISTENER_KEY).forEach(obs => obs.next(info))
  }

  /**
   * 订阅Topic数据的变化，一般点击左侧的主题项会产生事件
   */
  listenTopicChange() {
    addListener<TodoTopicVO>(TodoService.LISTENER_KEY.topic, {
      next: top => {
        this.mateInfo.initMateInfoMap();
        this.mateInfo.mateInfoMap.forEach((v, k) => {
          let needNotify = false;
          if (v.topic.show) {
            v.topic.show = false;
            needNotify = true;
          }
          if (!v.topic.expand) {
            v.topic.expand = true;
            needNotify = true;
          }
          // 将mateinfo变化的事件广播分发出去
          if (needNotify) this.notify(v)
        })
      },
      error: (menu: MenuItemInfo) => {
        this.mateInfo.initMateInfoMap();
        let pre = null;
        menu.items.forEach(it => {
          let info = this.mateInfo.getOrDefault(it.id)
          let preShow = info.topic.show;
          let needNotify = false;
          info.topic.show = pre == undefined || it.topic?.id != pre.topic?.id
          needNotify = preShow != info.topic.show;
          needNotify = needNotify || !info.topic.expand
          info.topic.expand = true;
          if (needNotify) this.notify(info)
          pre = it;
        })
      },
      complete: () => null
    })
  }
}
