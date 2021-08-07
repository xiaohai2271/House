import {Component, Directive, EventEmitter, Input, OnInit, Output, ViewChild} from '@angular/core';
import {TodoTopicVO} from "../../../entity/viewobject/TodoTopicVO";
import {MenuItemInfo} from "../../utils/Menu";
import {TodoService} from "../../todo.service";
import {NzContextMenuService, NzDropdownMenuComponent} from "ng-zorro-antd/dropdown";

@Component({
  selector: 'app-topic-list',
  templateUrl: './topic-list.component.html',
})
export class TopicListComponent implements OnInit {

  constructor(
    public todoService: TodoService,
    private nzContextMenuService: NzContextMenuService
  ) {
  }

  @Output() deleteTopicEvent: EventEmitter<TodoTopicVO> = new EventEmitter<TodoTopicVO>();
  @Output() updateTopicEvent: EventEmitter<TodoTopicVO> = new EventEmitter<TodoTopicVO>();
  public topic: TodoTopicVO;
  public deleteModelData: { visible: boolean, onCancel: () => void, onOK: () => void } = {
    visible: false,
    onCancel: () => {
      this.deleteModelData.visible = false;
    },
    onOK: () => {
      this.deleteModelData.visible = false;
      this.deleteTopicEvent.emit(this.topic)
    }
  }

  editData: { editStatus: boolean, data: string } = {
    editStatus: false,
    data: ""
  }

  ngOnInit(): void {
  }

  setTopic = (topic: TodoTopicVO, menuItem?: MenuItemInfo) => {
    this.todoService.topic = menuItem ? menuItem : topic;
  }

  createRightClickPop($event: MouseEvent, menu: NzDropdownMenuComponent, topicVO: TodoTopicVO) {
    this.nzContextMenuService.create($event, menu);
    this.topic = topicVO;
    this.editData.data = topicVO.title;
  }

  deleteTopic = () => this.deleteModelData.visible = true


  renameTopic = (status: boolean = true) => {
    this.editData.editStatus = status;
    if (status) {
      setTimeout(() => {
        const inputEl: any = document.getElementById(`input-${this.topic.id}`)
        inputEl.select()
        inputEl.focus()
      }, 100);
    } else {
      if (this.topic.title == this.editData.data) return
      this.topic.title = this.editData.data;
      this.editData.data = "";
      this.updateTopicEvent.emit(this.topic)
    }
  }
}
