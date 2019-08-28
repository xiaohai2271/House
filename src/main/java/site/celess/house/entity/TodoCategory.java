package site.celess.house.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @Author: 小海
 * @Date： 2019/07/27 15:32
 * @Description： todo的分类
 */
@Data
@Entity
@Table(name = "todo_category")
public class TodoCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tc_id")
    private Integer id;

    @Column(name = "tc_name")
    private String name;

    @JsonIgnore
    @Column(name = "tc_todo")
    public String todo;

    @Column(name = "tc_time")
    private Long time;

    public List<Integer> getTodo() {
        String[] tmpTodo = todo.substring(1, todo.length() - 1).split(",");
        List<Integer> todoInt = new ArrayList<>();
        for (String s : tmpTodo) {
            if (!"".equals(s)) {
                todoInt.add(Integer.parseInt(s));
            }
        }
        return todoInt;
    }

    public void setTodo(List<Integer> todoList) {
        // 去除“, ”后面的空格
        this.todo = todoList.toString().replaceAll(", ", ",");
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public Date getTime() {
        return new Date(time);
    }
}
