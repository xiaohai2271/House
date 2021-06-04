package cn.celess.house.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import cn.celess.house.entity.TodoTopic;

/**
 * (TdTopic)表数据库访问层
 *
 * @author 禾几海
 * @since 2021-06-05 00:42:01
 */
@Repository
public interface TodoTopicDao extends JpaRepository<TodoTopic,Integer> {

}
