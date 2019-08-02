package site.celess.house.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.celess.house.entity.TodoCategory;

/**
 * @Author: 小海
 * @Date： 2019/07/27 15:36
 * @Description：
 */
public interface TodoCategoryRepository extends JpaRepository<TodoCategory,Integer> {
}
