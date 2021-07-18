import {Component, EventEmitter, Input, OnChanges, Output} from '@angular/core';
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
export class ItemDetailComponent implements OnChanges {

  @Input() drawer: DrawerData;
  @Output() drawerChange: EventEmitter<DrawerData> = new EventEmitter<DrawerData>();

  constructor(public todoService: TodoService) {
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

  dataChanged() {

  }

  inEditMode = () => {
    this.drawer.editable = true;
  }
}
