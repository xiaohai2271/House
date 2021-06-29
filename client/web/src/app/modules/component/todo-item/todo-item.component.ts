import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {TodoItemVO} from "../../entity/viewobject/TodoItemVO";

@Component({
  selector: 'app-todo-item',
  templateUrl: './todo-item.component.html',
  styleUrls: ['./todo-item.component.less']
})
export class TodoItemComponent implements OnInit {

  constructor() {
  }

  @Input("data") data: TodoItemVO;
  @Output("onStatusChanged") onStatusChanged: EventEmitter<boolean> = new EventEmitter<boolean>();
  @Output("onDelete") onDelete: EventEmitter<TodoItemVO> = new EventEmitter<TodoItemVO>();
  @Output("onEdit") onEdit: EventEmitter<TodoItemVO> = new EventEmitter<TodoItemVO>();
  @Output("contentClick") contentClick: EventEmitter<void> = new EventEmitter<void>();

  ngOnInit(): void {
  }


  setItemStatus() {
    this.onStatusChanged.emit(!this.data.done)
    this.data.done = !this.data.done;
  }

  delete() {
    this.onDelete.emit(this.data);
  }

  edit() {
    this.onEdit.emit(this.data);
  }
}
