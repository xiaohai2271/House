import React, { FC, useEffect, useState } from "react";
import { BrowserRouter as Router, Switch, Route, Link } from "react-router-dom";

import "./App.less";
import { Layout, Menu } from "antd";
import TopicList, {
  TodoTopicListProps,
} from "./components/TopicList/TopicList";
import { TopicApis } from "@/view/http/apis";
import { TodoTopicVO } from "@/view/entity/viewobject/TodoTopicVO";
import TopicDetail from "@/view/components/TodoDetail/TodoDetail";

const { Sider } = Layout;
const { UserInfo, TodoTopicList } = TopicList;

const ToDoApp: FC = () => {
  const [list, setList] = useState<TodoTopicVO[]>([]);
  const [topic, setTopic] = useState<{
    topicId?: number | string;
    topicName?: string;
  }>({});

  useEffect(() => {
    let observable = TopicApis.query();
    observable.subscribe((observer) => {
      setList(observer.data);
    });
  }, []);
  const onMenuItemChanged = (data: {
    key: string;
    title: string;
    topicData?: TodoTopicVO;
  }) => {
    setTopic({ topicId: data.key, topicName: data.title });
  };

  return (
    <Layout className="layout">
      <Sider
        width="320"
        theme="light"
        breakpoint="xs"
        className="sider"
        collapsedWidth="0"
        onBreakpoint={(broken) => {
          console.log(broken);
        }}
        onCollapse={(collapsed, type) => {
          console.log(collapsed, type);
        }}
      >
        <div className="logo">House To Do</div>
        <UserInfo />
        <TodoTopicList data={list} onMenuItemChanged={onMenuItemChanged} />
      </Sider>
      <TopicDetail topicData={topic} />
    </Layout>
  );
};

export default ToDoApp;
