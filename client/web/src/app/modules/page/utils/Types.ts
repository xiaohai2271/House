import {TodoTopicVO} from "../../entity/viewobject/TodoTopicVO";

export const isTodoTopicVO = (a: any): a is TodoTopicVO => typeof (a as TodoTopicVO)["id"] != "undefined";


export class ModalData<T> {
  visible?: boolean = false;
  data?: T;
  onCancel?: Function = () => this.visible = false;
  onOk?: Function = () => this.onCancel();
  onShow?: Function = () => this.visible = true;
  onClose?: Function = () => this.visible = false;
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
  return obj;
}


export function copyOf<T>(src: any): T {
  let result = <T>{};
  for (let id in src) {
    (<any>result)[id] = (<any>src)[id];
  }
  return result;
}
