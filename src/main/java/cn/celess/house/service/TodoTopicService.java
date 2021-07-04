package cn.celess.house.service;

import cn.celess.house.entity.TodoTopic;
import cn.celess.house.entity.dto.TodoTopicDTO;
import cn.celess.house.entity.vo.TodoTopicVO;
import org.springframework.stereotype.Service;

/**
 * (TdTopic)表服务接口
 *
 * @author 禾几海
 * @since 2021-06-05 00:42:01
 */
@Service
public interface TodoTopicService extends IBaseService<TodoTopic, Integer, TodoTopicVO, TodoTopicDTO> {
}
