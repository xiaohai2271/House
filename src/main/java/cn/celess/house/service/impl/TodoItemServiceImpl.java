package cn.celess.house.service.impl;

import cn.celess.house.dao.TodoItemDao;
import cn.celess.house.dao.TodoTopicDao;
import cn.celess.house.entity.TodoItem;
import cn.celess.house.entity.vo.TodoItemVO;
import cn.celess.house.entity.dto.TodoItemDTO;
import cn.celess.house.service.TodoItemService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author: 小海
 * @date： 2021/06/05 12:29
 * @description：
 */
@Service
public class TodoItemServiceImpl extends BaseServiceImpl<TodoItem, Integer, TodoItemVO, TodoItemDTO> implements TodoItemService {
    @Resource
    private TodoItemDao todoItemDao;
    @Resource
    private TodoTopicDao todoTopicDao;

    @Override
    public JpaRepository<TodoItem, Integer> getJpaRepository() {
        return todoItemDao;
    }

    @Override
    public TodoItemVO insert(TodoItemDTO todoItem) {
        todoItem.setCreateDate(new Date());
        return super.insert(todoItem);
    }

    @Override
    public List<TodoItemVO> queryAllByTopic(Integer topicId) {
        return todoItemDao.findAllByTopicId(topicId)
                .stream()
                .map(TodoItem::toViewObject)
                .collect(Collectors.toList());
    }

    @Override
    public TodoItemVO afterExecution(TodoItem entity, Function<TodoItem, TodoItemVO> function) {
        TodoItemVO vo = super.afterExecution(entity, function);
        if (entity.getTopicId() == null) {
            return vo;
        }
        vo.setTopic(todoTopicDao.getById(entity.getTopicId()).toViewObject());
        return vo;
    }
}
