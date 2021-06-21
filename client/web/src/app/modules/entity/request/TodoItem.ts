export interface TodoItem {
  completeDate?: Date;
  createDate: Date;
  deadlineDate?: Date;
  description?: string;
  id: number | undefined;
  title: string;
  topicId: number | undefined;
}
