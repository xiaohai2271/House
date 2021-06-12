import React, { FC, useEffect, useRef, useState } from "react";
import { Avatar, Col, Divider, Menu, Row } from "antd";
import "./TopicList.less";
import { TodoTopicVO } from "@/view/entity/viewobject/TodoTopicVO";

import {
  SearchOutlined,
  HomeOutlined,
  UserOutlined,
  CalendarOutlined,
  MenuOutlined,
  CheckCircleOutlined,
  UnorderedListOutlined,
} from "@ant-design/icons";

const UserInfo: FC = () => (
  <Row className="user-info">
    <Col flex="32px">
      <Avatar size={32} icon={<UserOutlined />} />
    </Col>
    <Col flex="auto">
      <div style={{ paddingLeft: "10px", lineHeight: "100%" }}>
        <Row justify="start">
          <div style={{ display: "inline-block", width: "100%" }}>禾几海</div>
        </Row>
        <Row justify="start">
          <span>info...</span>
        </Row>
      </div>
    </Col>
    <Col flex="40px">
      <SearchOutlined />
    </Col>
  </Row>
);

export interface TodoTopicListProps {
  onMenuItemChanged?: (data: {
    key: string;
    title: string;
    topicData?: TodoTopicVO;
  }) => void;
  data: TodoTopicVO[];
}

const TodoTopicList: FC<TodoTopicListProps> = (props) => {
  const data = props.data;
  const onMenuItemChanged = props.onMenuItemChanged;

  const menuData = [
    { title: "已计划日程", key: "plan", icon: <CalendarOutlined /> },
    { title: "全部", key: "all", icon: <MenuOutlined /> },
    { title: "已完成", key: "done", icon: <CheckCircleOutlined /> },
    { title: "任务", key: "task", icon: <HomeOutlined /> },
  ];
  let selectedTopicData: {
    key: string;
    title: string;
    topicData?: TodoTopicVO;
  } = {
    key: "all",
    title: "全部",
  };
  useEffect(() => {
    if (onMenuItemChanged) {
      onMenuItemChanged(selectedTopicData);
    }
  }, []);
  const onMenuItemChangedHandler = (info: { key: string }) => {
    if (isNaN(Number(info.key))) {
      const menuItem = menuData.find((menu) => menu.key == info.key);
      if (!menuItem) return;
      selectedTopicData = {
        key: info.key,
        title: menuItem.title,
      };
    } else {
      const topicItem = data.find((topic) => topic.id == Number(info.key));
      if (!topicItem) return;
      selectedTopicData = {
        key: info.key,
        title: topicItem.title,
        topicData: topicItem,
      };
    }
    onMenuItemChanged && onMenuItemChanged(selectedTopicData);
  };

  return (
    <Menu
      theme="light"
      mode="inline"
      defaultSelectedKeys={["all"]}
      onSelect={onMenuItemChangedHandler}
    >
      {menuData.map((menu) => (
        <Menu.Item key={menu.key} icon={menu.icon}>
          {menu.title}
        </Menu.Item>
      ))}
      <Menu.Divider />

      {data.map((topic) => (
        <Menu.Item key={topic.id} icon={<UnorderedListOutlined />}>
          {topic.title}
        </Menu.Item>
      ))}
    </Menu>
  );
};

export default { UserInfo, TodoTopicList };
