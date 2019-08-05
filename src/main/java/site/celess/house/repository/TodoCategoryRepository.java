package site.celess.house.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import site.celess.house.entity.TodoCategory;

/**
 * @Author: 小海
 * @Date： 2019/07/27 15:36
 * @Description：
 */
@Repository
public interface TodoCategoryRepository extends JpaRepository<TodoCategory, Integer> {

    @Override
    boolean existsById(Integer id);

    boolean existsByName(String name);

    TodoCategory findTodoCategoryById(Integer id);

    @Query("select id from TodoCategory where name=?1")
    Integer getIdByName(String name);
}

