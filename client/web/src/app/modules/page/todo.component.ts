import {Component, OnInit} from '@angular/core';
import {NzIconService} from "ng-zorro-antd/icon";
import {TopicApis} from "../../http/apis";
import {TodoTopicVO} from "../entity/viewobject/TodoTopicVO";


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
  constructor(private iconService: NzIconService) {
    this.iconService.fetchFromIconfont({
      scriptUrl: '//at.alicdn.com/t/font_2623130_7yx6oyw5wsp.js'
    });

    this.menuInfo = [
      {name: '全部', id: -1, show: true, icon: 'icon-all_noselects'},
      {name: '计划', id: -1, show: true, icon: 'icon-calendar'},
      {name: '已完成', id: -1, show: true, icon: 'icon-done'},
      {name: '任务', id: -1, show: true, icon: 'icon-plan'},
    ]
  }

  public menuInfo: MenuItemInfo[] = [];
  public topicList: TodoTopicVO[] = [];

  ngOnInit(): void {
    TopicApis.query().subscribe(obs => {
      console.log(obs.data)
      this.topicList = obs.data;
    })
  }


}
