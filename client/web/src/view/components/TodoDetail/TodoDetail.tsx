import "./TodoDetail.less";
import { Layout, Menu } from "antd";
import { FC } from "react";
import { PlusOutlined } from "@ant-design/icons";
import React from "react";

const { Header, Content, Sider } = Layout;

export interface TopicDetailProps {
  topicData: {
    topicId?: number | string;
    topicName?: string;
  };
}

const TopicDetail: FC<TopicDetailProps> = (props) => {
  const topicName = props.topicData.topicName;
  const topicId = props.topicData.topicId;

  return (
    <Layout className="right-layout">
      <Header className="header" style={{ padding: 0 }}>
        <span className="title">{topicName}</span>
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
};

export default TopicDetail;
