import {TodoTopicVO} from "../../entity/viewobject/TodoTopicVO";
import {Observable, Observer} from "rxjs";

export const isTodoTopicVO = (a: any): a is TodoTopicVO => typeof (a as TodoTopicVO)["id"] != "undefined";

export class ModalData<T> {
  private _visible?: boolean = false;
  data?: T;
  private _observer?: Observer<boolean>;
  visibleListener?: Observable<boolean> = new Observable<boolean>(obs => this._observer = obs);
  onCancel?: Function = () => this.onClose();
  onOk?: Function = () => this.onClose();
  onShow?: Function = () => this.visible = true;
  onClose?: Function = () => this.visible = false;

  set visible(v: boolean) {
    this._visible = v;
    this._observer?.next(this._visible)
  }

  get visible() {
    return this._visible;
  }

}

export declare interface Weak {
  nextMon: number,
  nextSat: number,
  tomorrow: number,
  choose: number,
  clear: number
}

export declare interface MateInfo<T> {
  topic: {
    show: boolean,
    expand: boolean
  },
  data: T
}

export type Sortable = (o1: any, o2: any) => number
