import {Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';

@Component({
  selector: 'app-item-classify',
  template: `
    <div>
      <div class="tip" (click)="expandChange()">
        <i nz-icon [nzType]="expand?'down':'right'" nzTheme="outline"></i>
        <span class="tip-title">{{title || "未分类"}}</span>
      </div>
    </div>
  `,
  styles: [`
    .tip {
      margin: 20px 0 0 10px;
      background: rgba(220, 220, 220, .3);
      padding: 5px 10px;
      border-radius: 5px;
      display: inline-block;
    }

    .tip-title {
      margin-left: 10px;
      font-size: medium;
    }
  `]
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
