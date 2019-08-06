package site.celess.house.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import site.celess.house.entity.Todo;

import java.util.List;

/**
 * @Author: 小海
 * @Date： 2019/07/27 16:15
 * @Description：
 */
@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer> {
    Todo findTodosByid(Integer id);

    List<Todo> findAllByCategory(Integer categoryId);

    @Modifying
    @Transactional
    @Query("delete from Todo  where id in (?1)")
    void deleteBatch(List<Integer> ids);
}
