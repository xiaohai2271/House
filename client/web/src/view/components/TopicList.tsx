import React, { FC, useState } from "react";
import { Avatar, Col, Divider, Menu, Row } from "antd";
import { TopicApis } from "../http/apis";
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

const TodoTopicList: FC = () => {
  let observable = TopicApis.query();
  const [list, setList] = useState<TodoTopicVO[]>([]);
  observable.subscribe((observer) => {
    setList(observer.data);
  });

  return (
    <Menu theme="light" mode="inline" defaultSelectedKeys={["2"]}>
      <Menu.Item key="1" icon={<CalendarOutlined />}>
        已计划日程
      </Menu.Item>
      <Menu.Item key="2" icon={<MenuOutlined />}>
        全部
      </Menu.Item>
      <Menu.Item key="3" icon={<CheckCircleOutlined />}>
        已完成
      </Menu.Item>
      <Menu.Item key="4" icon={<HomeOutlined />}>
        任务
      </Menu.Item>
      <Menu.Divider />

      {list.map((topic) => (
        <Menu.Item key={topic.id + 5} icon={<UnorderedListOutlined />}>
          {topic.title}
        </Menu.Item>
      ))}
    </Menu>
  );
};

export default { UserInfo, TodoTopicList };
