package cn.celess.house.controller.api;

import cn.celess.house.entity.Response;
import cn.celess.house.entity.TodoTopic;
import cn.celess.house.entity.dto.TodoTopicDTO;
import cn.celess.house.enums.ResponseEnum;
import cn.celess.house.exception.ResponseException;
import cn.celess.house.service.TodoTopicService;
import cn.celess.house.util.ResponseUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.stream.Collectors;

/**
 * @author: 小海
 * @date： 2021/06/10 1:27
 * @description：
 */
@RestController
@RequestMapping("/api/todoTopic")
public class TodoTopicController {

    @Resource
    TodoTopicService todoTopicService;

    @PostMapping("/create")
    public Response create(@RequestBody TodoTopicDTO topicDto) {
        if (topicDto.getId() != null) {
            throw new ResponseException(ResponseEnum.PARAMETER_PK_NOT_NULL);
        }
        return ResponseUtil.success(
                todoTopicService.insert(
                        topicDto.toEntity()
                ).toViewObject()
        );
    }

    @DeleteMapping("/delete")
    public Response delete(@RequestBody TodoTopicDTO topicDto) {
        if (topicDto.getId() == null) {
            throw new ResponseException(ResponseEnum.PARAMETER_PK_NULL);
        }
        return ResponseUtil.success(todoTopicService.remove(topicDto.getId()));
    }

    @DeleteMapping("/delete/ids")
    public Response delete(@RequestBody Integer[] ids) {
        if (ids == null || ids.length == 0) {
            return ResponseUtil.success(null);
        }
        return ResponseUtil.success(todoTopicService.remove(ids));
    }

    @PutMapping("/update")
    public Response update(@RequestBody TodoTopicDTO topicDto) {
        if (topicDto.getId() == null) {
            throw new ResponseException(ResponseEnum.PARAMETER_PK_NULL);
        }
        return ResponseUtil.success(todoTopicService.update(topicDto.toEntity()));
    }

    @GetMapping("/{id}")
    public Response queryOne(@PathVariable("id") Integer id) {
        return ResponseUtil.success(
                todoTopicService.queryById(id).toViewObject()
        );
    }

    @GetMapping("/")
    public Response query() {
        return ResponseUtil.success(todoTopicService.queryAll().stream().map(TodoTopic::toViewObject).collect(Collectors.toList()));
    }
}
