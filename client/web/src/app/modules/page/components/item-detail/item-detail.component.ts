import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {TodoItemVO} from "../../../entity/viewobject/TodoItemVO";
import {TodoService} from "../../todo.service";

export interface DrawerData {
  visible: boolean,
  data?: TodoItemVO,
  close: () => void,
  dataChanged: boolean,
  deleteItem: (data: TodoItemVO) => boolean,
  editable: boolean
}

@Component({
  selector: 'app-item-detail',
  templateUrl: './item-detail.component.html',
  styleUrls: ['./item-detail.component.less']
})
export class ItemDetailComponent implements OnInit {

  @Input() drawer: DrawerData;
  @Output() drawerChange: EventEmitter<DrawerData> = new EventEmitter<DrawerData>();


  constructor(public todoService: TodoService) {
  }

  ngOnInit(): void {
  }

  deleteItem(data: TodoItemVO) {
    this.drawer.deleteItem(data);
    this.drawer.visible = false;
  }

  goBack() {
    this.drawer.dataChanged = false;
    this.drawer.close();
  }

  dataChanged() {
    this.drawer.dataChanged = true;
  }
}
