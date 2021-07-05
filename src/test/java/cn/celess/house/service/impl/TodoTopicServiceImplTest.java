package cn.celess.house.service.impl;

import cn.celess.house.AbstractTest;
import cn.celess.house.entity.vo.TodoItemVO;
import cn.celess.house.entity.vo.TodoTopicVO;
import cn.celess.house.service.TodoItemService;
import cn.celess.house.service.TodoTopicService;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

class TodoTopicServiceImplTest extends AbstractTest {

    @Resource
    TodoItemService todoItemService;

    @Resource
    TodoTopicService todoTopicService;

    @Test
    void afterExecution() {
        TodoItemVO todoItemVO = todoItemService.queryAll().stream().filter(it -> it.getTopic() != null).findAny().orElse(null);
        assertNotNull(todoItemVO);
        System.out.println(todoItemVO);

        todoItemVO = todoItemService.queryById(todoItemVO.getId());
        assertNotNull(todoItemVO);
        System.out.println(todoItemVO);


        //========================================
        TodoTopicVO todoTopicVO = todoTopicService.queryAll().stream().filter(top -> top.getItems() != null && top.getItems().size() > 0).findAny().orElse(null);
        assertNotNull(todoTopicVO);
        System.out.println(todoTopicVO);

        todoTopicVO = todoTopicService.queryById(todoTopicVO.getId());
        assertNotNull(todoTopicVO);
        System.out.println(todoTopicVO);

    }
}