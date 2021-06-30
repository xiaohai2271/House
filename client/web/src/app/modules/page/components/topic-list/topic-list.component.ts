import {Component, OnInit} from '@angular/core';
import {TodoTopicVO} from "../../../entity/viewobject/TodoTopicVO";
import {MenuItemInfo} from "../../utils/Menu";
import {TodoService} from "../../todo.service";


@Component({
  selector: 'app-topic-list',
  templateUrl: './topic-list.component.html',
})
export class TopicListComponent implements OnInit {

  constructor(
    public todoService: TodoService
  ) {

  }

  public topic: TodoTopicVO;

  ngOnInit(): void {
  }

  setTopic = (topic: TodoTopicVO, menuItem?: MenuItemInfo) => this.todoService.topic = menuItem ? menuItem : topic;

}
