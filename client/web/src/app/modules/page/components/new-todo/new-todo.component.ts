import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {TodoItem} from "../../../entity/request/TodoItem";

@Component({
  selector: 'app-new-todo',
  templateUrl: './new-todo.component.html',
  styleUrls: ['./new-todo.component.less']
})
export class NewTodoComponent implements OnInit {

  constructor() {
  }

  ngOnInit(): void {

  }

  @Output() onCancel: EventEmitter<void> = new EventEmitter<void>();
  @Output() onOk: EventEmitter<TodoItem> = new EventEmitter<TodoItem>();

  data: {
    deadLineVisible: boolean,
    deadLine: Date,
    title: string,
    desc: string
  } = {
    deadLineVisible: false,
    deadLine: null,
    title: "",
    desc: ""
  }

  setDeadLineVisible() {
    this.data.deadLineVisible = true
    setTimeout(() => document.getElementById("datatimepicker").focus(), 100)
  }

  setUnVisible() {
    this.data.deadLineVisible = false;
  }

  onOkClick() {
    if (!this.data.title) return
    this.onOk.emit({
      createDate: new Date(),
      deadlineDate: this.data.deadLine,
      description: this.data.desc,
      title: this.data.title
    })
    this.data.desc = "";
    this.data.title = "";
    this.data.deadLine = null;
  }
}
