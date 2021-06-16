import "./TodoDetail.less";
import { Button, Col, Input, Layout, Row } from "antd";
import React, { ElementRef, FC, useEffect, useRef, useState } from "react";
import {
  CalendarOutlined,
  AlertOutlined,
  PlusCircleOutlined,
  PlusOutlined,
} from "@ant-design/icons";
import { TodoItemApis, TopicApis } from "@/view/http/apis";
import { TodoItem } from "@/view/entity/request/TodoItem";
import { TodoItemVO } from "@/view/entity/viewobject/TodoItemVO";
import Radio from "@/view/components/Radio/Radio";

const { Header, Content, Sider } = Layout;

export interface TopicDetailProps {
  topicData: {
    topicId?: number | string;
    topicName?: string;
  };
}

const TodoItem: FC<{ itemData: TodoItemVO }> = (props) => {
  let data = props.itemData;
  const [todoItem, setTodoItem] = useState<TodoItemVO>(data);
  const handler = (status: boolean) => {
    setTodoItem({ ...todoItem, done: status });
  };
  return (
    <li
      style={{
        listStyle: "none",
        background: "#fff",
        borderRadius: "5px",
        margin: "1px 0",
        minHeight: "50px",
        lineHeight: "50px",
      }}
    >
      <Radio
        checked={todoItem.done}
        checkStatusChanged={handler}
        style={{ marginLeft: "10px" }}
      />
      <span
        style={{
          marginLeft: "10px",
          textDecoration: todoItem.done ? "line-through" : "unset",
        }}
      >
        {todoItem.title}
      </span>
    </li>
  );
};
const TopicDetail: FC<TopicDetailProps> = (props) => {
  const topicName = props.topicData.topicName;
  const topicId = props.topicData.topicId;

  const [dataList, setDataList] = useState<TodoItemVO[]>([]);
  const sort = (a: TodoItemVO, b: TodoItemVO) => {
    return a.done ? -1 : b.done ? 1 : 0;
  };
  useEffect(() => {
    if (isNaN(Number(topicId))) {
      //todo 查询所有的数据
      TodoItemApis.query().subscribe((obs) => {
        const data = obs.data.sort(sort);
        switch (topicId) {
          case "all":
            setDataList(data);
            break;
          case "done":
            setDataList(data.filter((todo) => todo.done));
            break;
          case "task":
            setDataList(data.filter((todo) => !todo.done));
            break;
          case "plan":
            setDataList(data.filter((todo) => todo.deadlineDate));
            break;
          default:
            setDataList(data);
            break;
        }
      });
    } else {
      TopicApis.queryOne(Number(topicId)).subscribe((obs) => {
        setDataList(obs.data.items.sort(sort));
      });
    }
  }, [topicId]);

  const [showAddTodo, setShowAddTodo] = useState(false);
  const inputRef = useRef<Input>(null);

  const createTodoItem = (e: any) => {
    let value: string = inputRef.current?.input.value || "";
    console.log(value);
    const todoItemRequest: TodoItem = {
      id: undefined,
      title: value,
      topicId: isNaN(Number(topicId)) ? undefined : Number(topicId),
      createDate: new Date(),
    };
    TodoItemApis.create(todoItemRequest).subscribe((obs) => {
      console.log(todoItemRequest, obs);
    });
  };

  const addTask = showAddTodo ? (
    <Row wrap={false} align="middle">
      <Col flex="20px">
        <PlusCircleOutlined
          style={{ fontSize: "20px" }}
          onClick={() => setShowAddTodo(true)}
        />
      </Col>
      <Col flex="auto">
        <Input
          style={{ color: "white" }}
          placeholder="添加任务"
          bordered={false}
          ref={inputRef}
          hidden={!showAddTodo}
          onBlur={() => setShowAddTodo(false)}
          onPressEnter={createTodoItem}
        />
      </Col>
      <Col flex="40px">
        <Button
          ghost
          type="dashed"
          icon={<CalendarOutlined />}
          title="添加截止日期"
        />
      </Col>
    </Row>
  ) : (
    <Row>
      <Col flex="20px">
        <PlusOutlined style={{ fontSize: "20px" }} />
      </Col>
      <Col flex="auto">
        <span
          hidden={showAddTodo}
          style={{ marginLeft: "15px" }}
          onClick={() => {
            setShowAddTodo(true);
            setTimeout(() => inputRef?.current?.focus(), 30);
          }}
        >
          添加任务
        </span>
      </Col>
    </Row>
  );
  return (
    <Layout className="right-layout">
      <Header className="header" style={{ padding: 0 }}>
        <span className="title">{topicName}</span>
      </Header>
      <Content style={{ margin: " 0", maxHeight: "100%", overflow: "auto" }}>
        <div className="site-layout-background" style={{ minHeight: 360 }}>
          {dataList.map((todo) => (
            <TodoItem key={todo.id} itemData={todo} />
          ))}
        </div>
      </Content>
      <div className="footer add-task">{addTask}</div>
    </Layout>
  );
};

export default TopicDetail;
