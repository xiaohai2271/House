import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {TodoItemVO} from "../../entity/viewobject/TodoItemVO";
import {NzContextMenuService, NzDropdownMenuComponent} from "ng-zorro-antd/dropdown";
import {TodoService} from "../../page/todo.service";
import {TodoItem} from "../../entity/request/TodoItem";
import {TodoTopicVO} from "../../entity/viewobject/TodoTopicVO";

@Component({
  selector: 'app-todo-item',
  templateUrl: './todo-item.component.html',
  styleUrls: ['./todo-item.component.less']
})
export class TodoItemComponent implements OnInit {

  constructor(
    private nzContextMenuService: NzContextMenuService,
    public todoService: TodoService
  ) {
  }

  @Input("data") data: TodoItemVO;
  @Output("onStatusChanged") onStatusChanged: EventEmitter<boolean> = new EventEmitter<boolean>();
  @Output("onDelete") onDelete: EventEmitter<TodoItemVO> = new EventEmitter<TodoItemVO>();
  @Output("onEdit") onEdit: EventEmitter<TodoItemVO> = new EventEmitter<TodoItemVO>();
  @Output("onUpdate") onUpdate: EventEmitter<TodoItem> = new EventEmitter<TodoItem>();
  @Output("contentClick") contentClick: EventEmitter<void> = new EventEmitter<void>();

  ngOnInit(): void {
  }

  public deleteModelData: { visible: boolean, onCancel: () => void, onOK: () => void } = {
    visible: false,
    onCancel: () => {
      this.deleteModelData.visible = false;
    },
    onOK: () => {
      this.deleteModelData.visible = false;
      this.delete();
    }
  }

  setItemStatus() {
    this.onStatusChanged.emit(!this.data.done)
    this.data.done = !this.data.done;
  }

  delete = () => this.onDelete.emit(this.data);

  edit = () => this.onEdit.emit(this.data);

  update() {
    this.onUpdate.emit({
      completeDate: this.data.completeDate ? new Date(this.data.completeDate) : null,
      createDate: new Date(this.data.createDate),
      deadlineDate: new Date(this.data.deadlineDate),
      description: this.data.description,
      done: this.data.done,
      id: this.data.id,
      title: this.data.title,
      topicId: this.data.topic?.id || this.data.topicId
    })
  }

  createRightClickPop = ($event: MouseEvent, menu: NzDropdownMenuComponent) => {
    this.nzContextMenuService.create($event, menu);
  }

  deleteTask = () => {
    this.deleteModelData.visible = true
  }

  setTopic = (top: TodoTopicVO) => {
    this.data.topic = top;
    this.update()
  }

  setDoneStatus = () => {
    this.data.done = !this.data.done;
    this.update()
  }

  setDeadLine() {
    // TODO::
  }
}
