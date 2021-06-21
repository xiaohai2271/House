import {TodoTopic} from "../modules/entity/request/TodoTopic";
import {TodoItemVO} from "../modules/entity/viewobject/TodoItemVO";
import {TodoItem} from "../modules/entity/request/TodoItem";
import {HttpClient} from "@angular/common/http";
import {Response} from "../modules/entity/viewobject/Response";
import {TodoTopicVO} from "../modules/entity/viewobject/TodoTopicVO";

let instance: HttpClient;

export const TopicApis = {
  path: "/todo/topic",
  query: () => instance.get<Response<TodoTopicVO[]>>(TopicApis.path + "/"),
  queryOne: (id: number) => instance.get<Response<TodoTopicVO>>(TopicApis.path + "/" + id),
  create: (todoTopic: TodoTopic) => instance.post<Response<TodoTopicVO>>(TopicApis.path + "/create", todoTopic),
  deleteOne: (idNum: number) => instance.put<Response<boolean>>(TopicApis.path + `/delete/${idNum}`, null),
  deleteAll: (ids: number[]) => instance.put<Response<boolean>>(TopicApis.path + "/delete", ids),
  update: (todoTopic: TodoTopic) => instance.put<Response<TodoTopicVO>>(TopicApis.path + "/update", todoTopic)
}

export const TodoItemApis = {
  path: "/todo/item",
  query: () => instance.get<Response<TodoItemVO[]>>(TodoItemApis.path + "/"),
  create: (todoItem: TodoItem) => instance.post<Response<TodoItemVO>>(TodoItemApis.path + "/create", todoItem),
  deleteOne: (idNum: number) => instance.put<Response<boolean>>(TodoItemApis.path + `/delete/${idNum}`, null),
  deleteAll: (ids: number[]) => instance.put<Response<boolean>>(TodoItemApis.path + "/delete", ids),
  update: (todoItem: TodoItem) => instance.put<Response<TodoItemVO>>(TodoItemApis.path + "/update", todoItem),
};


export const setHttpClient = (client: HttpClient) => instance = client;
