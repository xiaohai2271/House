package site.celess.house.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import site.celess.house.entity.TodoCategory;

/**
 * @Author: 小海
 * @Date： 2019/07/27 15:36
 * @Description：TodoCategory dao
 */
@Repository
public interface TodoCategoryRepository extends JpaRepository<TodoCategory, Integer> {

    /**
     * as its name says ,it gets the data's  exist status by id
     *
     * @param id id
     * @return exist status
     */
    @Override
    boolean existsById(Integer id);

    /**
     * it gets the data's exist status by name
     *
     * @param name name
     * @return exist status
     */
    boolean existsByName(String name);

    /**
     * get one TodoCategory data by id
     *
     * @param id id
     * @return TodoCategory
     */
    TodoCategory findTodoCategoryById(Integer id);

    /**
     * it gets one data's id by data's name , it may not be used cause can get whole data to replace this method
     * @param name name
     * @return data's id
     */
    @Query("select id from TodoCategory where name=?1")
    Integer getIdByName(String name);
}

