import {
  Component,
  EventEmitter,
  Input,
  OnChanges,
  OnDestroy,
  OnInit,
  Output
} from '@angular/core';
import {TodoItemVO} from "../../../entity/viewobject/TodoItemVO";
import {TodoService} from "../../todo.service";

export interface DrawerData {
  visible: boolean,
  data?: TodoItemVO,
  close: () => void,
  checkDataChange: (oldVal: TodoItemVO, newVal: TodoItemVO) => boolean
  deleteItem: (data: TodoItemVO) => boolean,
  editable: boolean
}

@Component({
  selector: 'app-item-detail',
  templateUrl: './item-detail.component.html',
  styleUrls: ['./item-detail.component.less']
})
export class ItemDetailComponent implements OnChanges, OnInit, OnDestroy {

  @Input() drawer: DrawerData;
  @Output() drawerChange: EventEmitter<DrawerData> = new EventEmitter<DrawerData>();

  constructor(public todoService: TodoService) {
  }

  ngOnInit(): void {
    document.addEventListener("keyup", this.eventListener);
  }

  ngOnDestroy(): void {
    document.removeEventListener("keyup", this.eventListener)
  }

  ngOnChanges(): void {
    if (this.drawer.data) {
      this.drawer.data.topic = this.todoService.topicList.filter(top => top.id == this.drawer.data.topic?.id).pop()
    }
  }

  deleteItem(data: TodoItemVO) {
    this.drawer.deleteItem(data);
    this.drawer.visible = false;
  }

  inEditMode = () => {
    this.drawer.editable = true;
  }

  private eventListener = (e: KeyboardEvent) => {
    if (!this.drawer.visible) return
    if (e.code == 'Escape') this.drawer.editable = false;
    if (e.code == 'Enter') this.drawer.close();
  }
}
