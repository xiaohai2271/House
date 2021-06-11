import { TodoItemVO } from "@/view/entity/viewobject/TodoItemVO";

export interface TodoTopicVO {
  color: string;
  date: string;
  icon: string;
  id: number;
  items: TodoItemVO[];
  title: string;
  userId: number;
}
