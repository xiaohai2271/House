package cn.celess.house.controller.api;

import cn.celess.house.entity.BaseEntity;
import cn.celess.house.entity.Response;
import cn.celess.house.entity.TodoItem;
import cn.celess.house.entity.dto.TodoItemDTO;
import cn.celess.house.entity.vo.TodoItemVO;
import cn.celess.house.enums.ResponseEnum;
import cn.celess.house.exception.ResponseException;
import cn.celess.house.service.TodoItemService;
import cn.celess.house.util.ResponseUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

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
    public Response<TodoItemVO> create(@RequestBody TodoItemDTO todoItemDTO) {
        if (todoItemDTO.getId() != null) {
            throw new ResponseException(ResponseEnum.PARAMETER_PK_NOT_NULL);
        }
        return ResponseUtil.success(todoItemService.insert(todoItemDTO));
    }

    @PutMapping("/delete/{id}")
    public Response<Boolean> delete(@PathVariable("id") Integer id) {
        if (id == null) {
            throw new ResponseException(ResponseEnum.PARAMETER_PK_NULL);
        }
        return ResponseUtil.success(todoItemService.remove(id));
    }

    @PutMapping("/delete/ids")
    public Response<Boolean> delete(@RequestBody Integer[] ids) {
        if (ids == null || ids.length == 0) {
            return ResponseUtil.failure();
        }
        return ResponseUtil.success(todoItemService.remove(ids));
    }

    @PutMapping("/update")
    public Response<TodoItemVO> update(@RequestBody TodoItemDTO todoItemDTO) {
        if (todoItemDTO.getId() == null) {
            throw new ResponseException(ResponseEnum.PARAMETER_PK_NULL);
        }
        return ResponseUtil.success(todoItemService.update(todoItemDTO));
    }

    @GetMapping("/")
    public Response<List<TodoItemVO>> query() {
        return ResponseUtil.success(todoItemService.queryAll());
    }
}
