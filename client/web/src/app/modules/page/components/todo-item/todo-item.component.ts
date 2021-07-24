import {Component, EventEmitter, Input, NgZone, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';
import {TodoItemVO} from "../../../entity/viewobject/TodoItemVO";
import {NzContextMenuService, NzDropdownMenuComponent} from "ng-zorro-antd/dropdown";
import {TodoService} from "../../todo.service";
import {TodoItem} from "../../../entity/request/TodoItem";
import {TodoTopicVO} from "../../../entity/viewobject/TodoTopicVO";
import {createModalData, MateInfo, ModalData, Weak} from "../../utils/Types";
import {TodoItemService} from "./todo-item.service";

@Component({
  selector: 'app-todo-item',
  templateUrl: './todo-item.component.html',
  styleUrls: ['./todo-item.component.less']
})
export class TodoItemComponent implements OnInit, OnChanges {

  constructor(
    private nzContextMenuService: NzContextMenuService,
    public todoService: TodoService,
    public todoItemService: TodoItemService,
    private zone: NgZone
  ) {
  }

  @Input() data: TodoItemVO;
  // @Output("mateInfoChange") mateInfoChange: EventEmitter<MateInfo<TodoItemVO>> = new EventEmitter<MateInfo<TodoItemVO>>();
  @Output("onDelete") onDelete: EventEmitter<TodoItemVO> = new EventEmitter<TodoItemVO>();
  @Output("onEdit") onEdit: EventEmitter<TodoItemVO> = new EventEmitter<TodoItemVO>();
  @Output("onUpdate") onUpdate: EventEmitter<TodoItem> = new EventEmitter<TodoItem>();
  @Output("contentClick") contentClick: EventEmitter<void> = new EventEmitter<void>();

  ngOnInit(): void {

  }

  ngOnChanges(changes: SimpleChanges): void {
    // this.mateInfo = changes.mateInfo.currentValue;
  }

  public deleteModelData: ModalData<void> = createModalData({
    visible: false,
    onOk: () => {
      this.deleteModelData.visible = false;
      this.delete();
    }
  })
  choseDateData: ModalData<Date> = createModalData<Date>({
    visible: false,
    data: new Date(),
    onCancel: () => {
      this.choseDateData.visible = false
      this.choseDateData.data = new Date();
    },
    onOk: () => {
      this.data.deadlineDate = this.choseDateData.data.toString();
      this.choseDateData.onCancel();
      this.update();
    }
  });

  setDoneStatus = () => {
    this.data.done = !this.data.done;
    this.data.completeDate = this.data.done ? new Date().toString() : null;
    this.update()
  }

  delete = () => this.onDelete.emit(this.data);

  edit = () => this.onEdit.emit(this.data);

  update() {
    this.onUpdate.emit({
      completeDate: this.data.completeDate ? new Date(this.data.completeDate) : null,
      createDate: new Date(this.data.createDate),
      deadlineDate: this.data.deadlineDate ? new Date(this.data.deadlineDate) : null,
      description: this.data.description,
      done: this.data.done,
      id: this.data.id,
      title: this.data.title,
      topicId: this.data.topic?.id
    })
  }

  public readonly WEAK: Weak = {
    clear: -1,
    choose: 0,
    nextMon: 1,
    nextSat: 2,
    tomorrow: 3
  }

  createRightClickPop = ($event: MouseEvent, menu: NzDropdownMenuComponent) => {
    this.nzContextMenuService.create($event, menu);
  }

  deleteTask = () => {
    this.deleteModelData.visible = true
  }

  setTopic = (top: TodoTopicVO) => {
    if (top.id == this.data.topic?.id) {
      return
    }
    this.data.topic = top;
    this.update()
  }

  setDeadLine(weak: number) {
    let date = new Date()
    let jumpDay;
    switch (weak) {
      case this.WEAK.choose:
        this.choseDateData.visible = true;
        if (this.data.deadlineDate) this.choseDateData.data = new Date(this.data.deadlineDate);
        return
      case this.WEAK.tomorrow:
        date.setDate(date.getDate() + 1)
        break
      case this.WEAK.nextSat:
        jumpDay = this.calcJumpDay(date.getDay(), 6)
        jumpDay = jumpDay % 7;
        date.setDate(date.getDate() + jumpDay);
        break
      case this.WEAK.nextMon:
        jumpDay = this.calcJumpDay(date.getDay(), 1)
        date.setDate(date.getDate() + jumpDay);
        break
      case this.WEAK.clear:
        date = null;
        break
      default:
    }
    this.data.deadlineDate = date?.toISOString()
    this.update();
  }

  private calcJumpDay = (now: number, toDay: number) => (toDay <= now) ? -(now - toDay - 7) : toDay - now;

}
