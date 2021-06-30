import {Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';
import {TodoItemVO} from "../../../entity/viewobject/TodoItemVO";

export interface DrawerData {
  visible: boolean,
  data?: TodoItemVO,
  close: () => void,
  dataChanged: boolean,
  deleteItem: (data: TodoItemVO) => boolean
}

@Component({
  selector: 'app-item-detail',
  templateUrl: './item-detail.component.html',
  styleUrls: ['./item-detail.component.less']
})
export class ItemDetailComponent implements OnInit {

  @Input() drawer: DrawerData;
  @Output() drawerChange: EventEmitter<DrawerData> = new EventEmitter<DrawerData>();

  constructor() {
  }

  ngOnInit(): void {
  }

  deleteItem(data: TodoItemVO) {
    this.drawer.deleteItem(data);
    this.drawer.visible = false;
  }
}
