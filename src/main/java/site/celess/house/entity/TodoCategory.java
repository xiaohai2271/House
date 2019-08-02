package site.celess.house.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: 小海
 * @Date： 2019/07/27 15:32
 * @Description： todo的分类
 */
@Data
@Entity
public class TodoCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tc_id")
    private Integer id;

    @Column(name = "tc_name")
    private String name;

    @Column(name = "tc_todo")
    public String todo;

    @Column(name = "tc_time")
    private Long time;

    public Integer[] getTodo() {
        String[] tmpTodo = todo.substring(1, todo.length() - 1).split(",");
        List<Integer>  todoInt = new ArrayList<>();
        for (int i = 0; i < tmpTodo.length; i++) {
            if (!"".equals(tmpTodo[i])) {
                todoInt.add(Integer.parseInt(tmpTodo[i]));
            }
        }
        return (Integer[]) todoInt.toArray(new Integer[todoInt.size()]);
    }

    public void setTodo(Integer[] todo) {
        this.todo = Arrays.toString(todo).replaceAll(" ", "");
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }
}
