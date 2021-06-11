import axios, { AxiosPromise } from "axios";
import { observable, Observable, Subscriber } from "rxjs";
import { TodoTopic } from "@/view/entity/request/TodoTopic";
import { TodoTopicVO } from "@/view/entity/viewobject/TodoTopicVO";
import { Response } from "@/view/entity/viewobject/Response";

export const host = "http://127.0.0.1:8080/api/";

const instance = axios.create({
  baseURL: host,
  timeout: 1000,
});

export const TopicApis = {
  path: "todo/topic/",
  query: () => {
    return promiseToRxjs<Response<TodoTopicVO[]>>(instance.get(TopicApis.path));
  },
  queryOne: (id: number) => {
    return promiseToRxjs<Response<TodoTopicVO>>(
      instance.get(TopicApis.path + id),
    );
  },
  create: (todoTopic: TodoTopic) => {
    return promiseToRxjs<Response<TodoTopicVO>>(
      instance.post(TopicApis.path, todoTopic),
    );
  },
  deleteOne: (idNum: number) => {
    return promiseToRxjs<Response<boolean>>(
      instance.delete(TopicApis.path, { data: { id: idNum } }),
    );
  },
  deleteAll: (ids: number[]) => {
    return promiseToRxjs<Response<boolean>>(
      instance.delete(TopicApis.path, { data: ids }),
    );
  },
  update: (todoTopic: TodoTopic) => {
    return promiseToRxjs<Response<TodoTopicVO>>(
      instance.put(TopicApis.path, todoTopic),
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
