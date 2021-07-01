import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {TodoService} from "../../todo.service";
import {clearTime, isEqual} from "../../utils/Date";

@Component({
  selector: 'app-statistics',
  templateUrl: './statistics.component.html',
  styleUrls: ['./statistics.component.less']
})
export class StatisticsComponent implements OnInit {

  constructor(
    public todoService: TodoService
  ) {
  }

  ngOnInit(): void {
  }

  @Output() onDateChanged: EventEmitter<Date> = new EventEmitter<Date>();

  selectedValue = new Date();

  selectChange = (select: Date) => this.onDateChanged.emit(select);

  getBackground(date: Date): string {
    date.setHours(0, 0, 0, 0)
    const items = this.todoService.topic.items
    if (!items || !items.length)
      return "#fff"
    const dateItem = items.filter(top => isEqual(clearTime(new Date(top.createDate)), clearTime(date)));
    return dateItem.length ? (dateItem.filter(it => !it.done).length ? "#87d068" : "#e0e0e0") : "#fff"
  }
}
