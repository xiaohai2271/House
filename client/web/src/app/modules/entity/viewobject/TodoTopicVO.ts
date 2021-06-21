import {TodoItemVO} from "./TodoItemVO";

export interface TodoTopicVO {
  color: string;
  date: string;
  icon: string;
  id: number;
  items: TodoItemVO[];
  title: string;
  userId: number;
}
