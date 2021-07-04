package cn.celess.house.service;

import cn.celess.house.entity.TodoItem;
import cn.celess.house.entity.dto.TodoItemDTO;
import cn.celess.house.entity.vo.TodoItemVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (TdItem)表服务接口
 *
 * @author 禾几海
 * @since 2021-06-05 00:41:24
 */
@Service
public interface TodoItemService extends IBaseService<TodoItem, Integer, TodoItemVO, TodoItemDTO> {
    /**
     * 通过 topicId 来查询 items
     *
     * @param topicId 主键
     * @return List<TodoItemVO>
     */
    List<TodoItemVO> queryAllByTopic(Integer topicId);
}
