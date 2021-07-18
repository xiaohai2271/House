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

export function createModalData<T>(modalData?: ModalData<T>): ModalData<T> {
  const obj = new ModalData<T>();
  if (!modalData) return obj;
  obj.visible = modalData.visible || false;
  obj.data = modalData.data || undefined;
  obj.onCancel = modalData.onCancel || obj.onCancel;
  obj.onOk = modalData.onOk || obj.onOk;
  obj.onShow = modalData.onShow || obj.onShow;
  obj.onClose = modalData.onClose || obj.onClose;
  obj.visibleListener = modalData.visibleListener || obj.visibleListener;
  return obj;
}


export function copyOf<T>(src: any): T {
  let result = <T>{};
  for (let id in src) {
    (<any>result)[id] = (<any>src)[id];
  }
  return result;
}
