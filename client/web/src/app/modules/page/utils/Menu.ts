import {TodoItemVO} from "../../entity/viewobject/TodoItemVO";

export class MenuItemInfo {
  menuInfo: MenuID;
  icon: string;
  show: boolean;
  title?: string;
  items?: TodoItemVO[]
}

export declare class MenuID {
  id: number;
  name: string;
}

export const MENU: { [name: string]: MenuID } = {
  all: {id: -1, name: '全部'},
  plan: {id: -2, name: '计划'},
  done: {id: -3, name: '已完成'},
  task: {id: -4, name: '任务'}
}
