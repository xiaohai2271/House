import {TodoTopicVO} from "../../entity/viewobject/TodoTopicVO";

export const isTodoTopicVO = (a: any): a is TodoTopicVO => typeof (a as TodoTopicVO)["id"] != "undefined";
