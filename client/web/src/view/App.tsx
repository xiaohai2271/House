import React, { FC } from "react";
import { BrowserRouter as Router, Switch, Route, Link } from "react-router-dom";

import "./App.less";
import { Layout } from "antd";
import { PlusOutlined } from "@ant-design/icons";
import TopicList from "./components/TopicList";

const { Header, Content, Sider } = Layout;
const { UserInfo, TodoTopicList } = TopicList;

const TopicDetail: FC = () => (
  <Layout className="right-layout">
    <Header className="header" style={{ padding: 0 }}>
      <span className="title">任务1</span>
    </Header>
    <Content style={{ margin: " 0" }}>
      <div
        className="site-layout-background"
        style={{ minHeight: 360, background: "#f0f0f0" }}
      >
        content
      </div>
    </Content>
    <div className="footer add-task">
      <div style={{ marginLeft: "15px" }}>
        <PlusOutlined />
        <span style={{ marginLeft: "15px" }}>添加任务</span>
      </div>
    </div>
  </Layout>
);
const ToDoApp: FC = () => {
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
        <TodoTopicList />
      </Sider>
      <TopicDetail />
    </Layout>
  );
};

export default ToDoApp;
