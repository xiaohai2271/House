import {Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';

@Component({
  selector: 'app-item-classify',
  templateUrl: './item-classify.component.html',
  styleUrls: ['./item-classify.component.less']
})
export class ItemClassifyComponent implements OnInit, OnChanges {

  constructor() {
  }

  @Input("title") title: string
  @Input("expand") expand: boolean = true;
  @Output("expandChange") expandChangeEvent: EventEmitter<boolean> = new EventEmitter<boolean>();

  ngOnInit(): void {
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes.title && !changes?.title?.isFirstChange()) this.title = changes.title.currentValue;
    if (changes?.expand && !changes.expand?.isFirstChange()) this.expand = changes.expand.currentValue;
  }


  expandChange() {
    this.expand = !this.expand;
    this.expandChangeEvent.emit(this.expand)
  }
}
