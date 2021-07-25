import {ModalData} from "./Types";

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
