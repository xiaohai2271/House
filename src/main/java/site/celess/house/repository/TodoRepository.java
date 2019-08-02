package site.celess.house.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.celess.house.entity.Todo;

/**
 * @Author: 小海
 * @Date： 2019/07/27 16:15
 * @Description：
 */
public interface TodoRepository extends JpaRepository<Todo, Integer> {
}
