package cn.celess.house.service.impl;

import cn.celess.house.dao.TodoTopicDao;
import cn.celess.house.entity.TodoTopic;
import cn.celess.house.service.TodoTopicService;
import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 * @author: 小海
 * @date： 2021/06/05 12:32
 * @description：
 */
@Service
public class TodoTopicServiceImpl extends BaseServiceImpl<TodoTopic, Integer> implements TodoTopicService {

    private TodoTopicDao todoTopicDao;

    public TodoTopicServiceImpl(TodoTopicDao repository) {
        super(repository);
        this.todoTopicDao = repository;
    }
}
