package cn.celess.house.service.impl;

import cn.celess.house.dao.TodoItemDao;
import cn.celess.house.entity.TodoItem;
import cn.celess.house.entity.vo.TodoItemVO;
import cn.celess.house.entity.dto.TodoItemDTO;
import cn.celess.house.service.TodoItemService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
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
}
