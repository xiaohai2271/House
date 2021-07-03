import {TodoTopicVO} from "../../entity/viewobject/TodoTopicVO";

export const isTodoTopicVO = (a: any): a is TodoTopicVO => typeof (a as TodoTopicVO)["id"] != "undefined";


export class ModalData<T> {
  visible: boolean;
  data?: T;
  onCancel?: Function;
  onOk?: Function
}
