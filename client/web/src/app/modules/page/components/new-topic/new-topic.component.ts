import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {TodoTopic} from "../../../entity/request/TodoTopic";

@Component({
  selector: 'app-new-topic',
  template: `
    <div class="new-topic">
      <i nz-icon nzType="plus" nzTheme="outline"></i>
      <span style="margin-left: 10px;min-width: 80px"
            (click)="showAddTopic(AddTopic)"
            [style.display]="addTopicTipVisible?'inline':'none'"
            *ngIf="addTopicTipVisible">新建列表
        </span>
      <input #AddTopic
             nz-input
             nzBorderless
             type="text"
             (keydown.escape)="addTopicTipVisible= true"
             (keydown.enter)="submitTopicInfo($event)"
             (focusout)="addTopicTipVisible=true"
             [style.display]="addTopicTipVisible?'none':'inline'">
    </div>

  `,
})
export class NewTopicComponent implements OnInit {

  constructor() {
  }

  ngOnInit(): void {
  }

  public addTopicTipVisible: boolean = true;
  @Output() onCreateTopic: EventEmitter<TodoTopic> = new EventEmitter<TodoTopic>()

  submitTopicInfo(e: InputEvent) {
    // console.log((e.target as HTMLInputElement).value)
    const todoTopic: TodoTopic = {
      title: (e.target as HTMLInputElement).value,
      date: new Date(),
      userId: 1
    }
    this.onCreateTopic.emit(todoTopic)
    this.addTopicTipVisible = false;
    (e.target as HTMLInputElement).value = null;
  }


  showAddTopic(AddTopic: HTMLInputElement) {
    this.addTopicTipVisible = false;
    setTimeout(() => AddTopic.focus(), 100)
  }
}
