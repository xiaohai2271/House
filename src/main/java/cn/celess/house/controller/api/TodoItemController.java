package cn.celess.house.controller.api;

import cn.celess.house.entity.BaseEntity;
import cn.celess.house.entity.Response;
import cn.celess.house.entity.TodoItem;
import cn.celess.house.entity.dto.TodoItemDTO;
import cn.celess.house.service.TodoItemService;
import cn.celess.house.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: 小海
 * @date： 2021/06/05 14:42
 * @description：
 */
@RestController
@RequestMapping("/api")
public class TodoItemController {

    @Resource
    TodoItemService todoItemService;

    public Response create(TodoItemDTO todoItemDTO) {
        return ResponseUtil.success(
                todoItemService.insert(
                        todoItemDTO.toEntity()
                ).toViewObject()
        );
    }
}
