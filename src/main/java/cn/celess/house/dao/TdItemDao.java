package cn.celess.house.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import cn.celess.house.entity.TodoItem;

/**
 * (TdItem)表数据库访问层
 *
 * @author 禾几海
 * @since 2021-06-05 00:41:14
 */
@Repository
public interface TdItemDao extends JpaRepository<TodoItem, Integer> {

}
