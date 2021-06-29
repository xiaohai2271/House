import {TodoTopicVO} from "./TodoTopicVO";

export interface TodoItemVO {
  completeDate: string;
  createDate: string;
  deadlineDate: string;
  description: string;
  done: boolean;
  id: number;
  title: string;
  topicId: number;

  unDoneCount: number;
  topic?: TodoTopicVO;
}
