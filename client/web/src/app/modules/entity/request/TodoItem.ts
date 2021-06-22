export interface TodoItem {
  completeDate?: Date;
  createDate: Date;
  deadlineDate?: Date;
  description?: string;
  id?: number;
  title: string;
  topicId?: number;
}
