package site.celess.house.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import site.celess.house.entity.Response;
import site.celess.house.service.TodoService;
import site.celess.house.util.ResponseUtil;

/**
 * @Author: 小海
 * @Date： 2019/08/02 23:24
 * @Description：
 */
@RestController
@RequestMapping("/api")
public class TodoController {
    @Autowired
    TodoService todoService;

    @PostMapping("/todoCategory")
    public Response createTodoCategory(@RequestParam("name") String name) {
        return ResponseUtil.success(todoService.createTodoCategory(name));
    }

    @DeleteMapping("/todoCategory")
    public Response deleteTodoCategory(@RequestParam("id") Integer id) {
        return ResponseUtil.success(todoService.deleteTodoCategory(id));
    }

    @PutMapping("/todoCategory")
    public Response updateTodoCategory(@RequestParam("name") String name, @RequestParam("id") Integer id) {
        return ResponseUtil.success(todoService.updateTodoCategory(name, id));
    }

    @GetMapping("/todoCategory")
    public Response getAllTodoCategory() {
        return ResponseUtil.success(todoService.findAll());
    }


    @PostMapping("/todo")
    public Response createTodoItem(@RequestParam("desc") String desc,
                                   @RequestParam("categoryId") Integer categoryId) {
        return ResponseUtil.success(todoService.createTodoItem(desc, categoryId));
    }

    @DeleteMapping("/todo")
    public Response deleteTodoItem(@RequestParam("id") Integer id) {
        return ResponseUtil.success(todoService.deleteTodo(id));
    }

    @PutMapping("/todo")
    public Response updateTodoItem(@RequestParam(value = "desc", required = false) String desc,
                                   @RequestParam(value = "schedule", required = false) Integer schedule,
                                   @RequestParam("id") Integer id) {
        return ResponseUtil.success(todoService.updateTodoItem(desc, schedule, id));
    }

    @GetMapping("/todo/{categoryName}")
    public Response getTodoItem(@PathVariable("categoryName") String categoryName) {
        return ResponseUtil.success(todoService.findByCategory(categoryName));
    }


}
