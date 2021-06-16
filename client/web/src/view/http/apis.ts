import axios, { AxiosPromise } from "axios";
import { observable, Observable, Subscriber } from "rxjs";
import { TodoTopic } from "@/view/entity/request/TodoTopic";
import { TodoTopicVO } from "@/view/entity/viewobject/TodoTopicVO";
import { Response } from "@/view/entity/viewobject/Response";
import { TodoItemVO } from "@/view/entity/viewobject/TodoItemVO";
import { TodoItem } from "@/view/entity/request/TodoItem";

export const host = "http://127.0.0.1:8080/api";

const instance = axios.create({
  baseURL: host,
  timeout: 1000,
});

export const TopicApis = {
  path: "/todo/topic",
  query: () => {
    return promiseToRxjs<Response<TodoTopicVO[]>>(
      instance.get(TopicApis.path + "/"),
    );
  },
  queryOne: (id: number) => {
    return promiseToRxjs<Response<TodoTopicVO>>(
      instance.get(TopicApis.path + "/" + id),
    );
  },
  create: (todoTopic: TodoTopic) => {
    return promiseToRxjs<Response<TodoTopicVO>>(
      instance.post(TopicApis.path + "/create", todoTopic),
    );
  },
  deleteOne: (idNum: number) => {
    return promiseToRxjs<Response<boolean>>(
      instance.delete(TopicApis.path + "/delete", { data: { id: idNum } }),
    );
  },
  deleteAll: (ids: number[]) => {
    return promiseToRxjs<Response<boolean>>(
      instance.delete(TopicApis.path + "/delete", { data: ids }),
    );
  },
  update: (todoTopic: TodoTopic) => {
    return promiseToRxjs<Response<TodoTopicVO>>(
      instance.put(TopicApis.path + "/update", todoTopic),
    );
  },
};
export const TodoItemApis = {
  path: "/todo/item",
  query: () => {
    return promiseToRxjs<Response<TodoItemVO[]>>(
      instance.get(TodoItemApis.path + "/"),
    );
  },

  create: (todoItem: TodoItem) => {
    return promiseToRxjs<Response<TodoItemVO>>(
      instance.post(TodoItemApis.path + "/create", todoItem),
    );
  },
  deleteOne: (idNum: number) => {
    return promiseToRxjs<Response<boolean>>(
      instance.delete(TodoItemApis.path + "/delete", { data: { id: idNum } }),
    );
  },
  deleteAll: (ids: number[]) => {
    return promiseToRxjs<Response<boolean>>(
      instance.delete(TodoItemApis.path + "/delete", { data: ids }),
    );
  },
  update: (todoItem: TodoItem) => {
    return promiseToRxjs<Response<TodoItemVO>>(
      instance.put(TodoItemApis.path + "/update", todoItem),
    );
  },
};

function promiseToRxjs<T>(promise: AxiosPromise<T>): Observable<T> {
  return new Observable<T>((subscriber) => {
    promise
      .then((response) => subscriber.next(response.data))
      .catch((err) => subscriber.error(err))
      .then(() => subscriber.complete());
  });
}
