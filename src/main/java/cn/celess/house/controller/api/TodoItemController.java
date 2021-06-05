package cn.celess.house.controller.api;

import cn.celess.house.entity.BaseEntity;
import cn.celess.house.entity.Response;
import cn.celess.house.entity.TodoItem;
import cn.celess.house.entity.dto.TodoItemDTO;
import cn.celess.house.enums.ResponseEnum;
import cn.celess.house.exception.ResponseException;
import cn.celess.house.service.TodoItemService;
import cn.celess.house.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author: 小海
 * @date： 2021/06/05 14:42
 * @description：
 */
@RestController
@RequestMapping("/api/todo/item")
public class TodoItemController {

    @Resource
    TodoItemService todoItemService;

    @PostMapping("/create")
    public Response create(@RequestBody TodoItemDTO todoItemDTO) {
        if (todoItemDTO.getId() != null) {
            throw new ResponseException(ResponseEnum.PARAMETER_PK_NOT_NULL);
        }
        return ResponseUtil.success(
                todoItemService.insert(
                        todoItemDTO.toEntity()
                ).toViewObject()
        );
    }

    @DeleteMapping("/delete")
    public Response delete(@RequestBody TodoItemDTO todoItemDTO) {
        if (todoItemDTO.getId() == null) {
            throw new ResponseException(ResponseEnum.PARAMETER_PK_NULL);
        }
        return ResponseUtil.success(todoItemService.remove(todoItemDTO.getId()));
    }

    @DeleteMapping("/delete/ids")
    public Response delete(@RequestBody Integer[] ids) {
        if (ids == null || ids.length == 0) {
            return ResponseUtil.success(null);
        }
        return ResponseUtil.success(todoItemService.remove(ids));
    }

    @PutMapping("/update")
    public Response update(@RequestBody TodoItemDTO todoItemDTO) {
        if (todoItemDTO.getId() == null) {
            throw new ResponseException(ResponseEnum.PARAMETER_PK_NULL);
        }
        return ResponseUtil.success(todoItemService.update(todoItemDTO.toEntity()));
    }

    @GetMapping("/")
    public Response query() {
        return ResponseUtil.success(todoItemService.queryAll());
    }
}
