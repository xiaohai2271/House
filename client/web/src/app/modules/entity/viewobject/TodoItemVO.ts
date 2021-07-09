import {TodoTopicVO} from "./TodoTopicVO";

export class TodoItemVO {
  completeDate: string;
  createDate: string;
  deadlineDate: string;
  description: string;
  done: boolean;
  id: number;
  title: string;
  topic?: TodoTopicVO;

  unDoneCount: number;
}
