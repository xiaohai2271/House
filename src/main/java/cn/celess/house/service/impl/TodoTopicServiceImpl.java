package cn.celess.house.service.impl;

import cn.celess.house.dao.TodoItemDao;
import cn.celess.house.dao.TodoTopicDao;
import cn.celess.house.entity.TodoItem;
import cn.celess.house.entity.TodoTopic;
import cn.celess.house.entity.dto.TodoTopicDTO;
import cn.celess.house.entity.vo.TodoTopicVO;
import cn.celess.house.service.TodoItemService;
import cn.celess.house.service.TodoTopicService;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: 小海
 * @date： 2021/06/05 12:32
 * @description：
 */
@Service
public class TodoTopicServiceImpl extends BaseServiceImpl<TodoTopic, Integer, TodoTopicVO, TodoTopicDTO> implements TodoTopicService {

    @Resource
    private TodoTopicDao todoTopicDao;

    @Resource
    private TodoItemService todoItemService;

    @Override
    public JpaRepository<TodoTopic, Integer> getJpaRepository() {
        return todoTopicDao;
    }

    @Override
    public TodoTopicVO queryById(Integer integer) {
        TodoTopicVO todoTopicVO = super.queryById(integer);
        todoTopicVO.setItems(todoItemService.queryAllByTopic(integer));
        return todoTopicVO;
    }

    @Override
    public List<TodoTopicVO> queryAll() {
        return super.queryAll().stream()
                .peek(top -> top.setItems(todoItemService.queryAllByTopic(top.getId())))
                .collect(Collectors.toList());
    }
}
