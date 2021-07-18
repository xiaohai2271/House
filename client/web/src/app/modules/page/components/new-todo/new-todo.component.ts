import {
  Component,
  ElementRef,
  EventEmitter,
  Input, OnDestroy,
  OnInit,
  Output,
  ViewChild
} from '@angular/core';
import {TodoItem} from "../../../entity/request/TodoItem";
import {ModalData} from "../../utils/Types";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-new-todo',
  templateUrl: './new-todo.component.html',
  styleUrls: ['./new-todo.component.less']
})
export class NewTodoComponent implements OnInit, OnDestroy {

  constructor() {
  }

  ngOnInit(): void {
    this.visibleListenerSubscription = this.modelData.visibleListener.subscribe(obs => {
      if (obs) {
        setTimeout(() => this.titleInput.nativeElement.focus(), 100)
      }
    });
  }

  ngOnDestroy(): void {
    this.visibleListenerSubscription.unsubscribe()
  }

  @Input() modelData: ModalData<void>;
  @Output() modelDataChange: EventEmitter<ModalData<void>> = new EventEmitter<ModalData<void>>();
  @Output() onCancel: EventEmitter<void> = new EventEmitter<void>();
  @Output() onOk: EventEmitter<TodoItem> = new EventEmitter<TodoItem>();

  @ViewChild("titleInput", {static: true}) titleInput: ElementRef;
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
  private visibleListenerSubscription: Subscription

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
