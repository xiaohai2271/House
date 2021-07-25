import {Observer} from "rxjs";

export type DATA_LISTENER_KEY = string;
export type Subscribe = { unsubscribe: () => void };

const _listeners = new Map<string, Observer<any>[]>();

export function addListener<T>(key: DATA_LISTENER_KEY, observer: Observer<T>): Subscribe {
  getListener(key).push(observer)
  return {
    unsubscribe: () => removeListener(key, observer)
  }
}

export function removeListener<T>(key: DATA_LISTENER_KEY, observer: Observer<T>) {
  getListener(key).splice(getListener(key).indexOf(observer), 1)
}

export function getListener<T>(key: string): Observer<T>[] {
  let res = _listeners.get(key);
  if (res == undefined) {
    _listeners.set(key, [])
  }
  return _listeners.get(key);
}

export function emptyListener(key: DATA_LISTENER_KEY) {
  _listeners.get(key).splice(0, _listeners.get(key).length)
}

export function notifyListener(key: DATA_LISTENER_KEY, fun: (observer: Observer<any>) => void) {
  getListener<any>(key).forEach(fun)
}
