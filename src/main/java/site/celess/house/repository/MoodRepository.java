package site.celess.house.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import site.celess.house.entity.Mood;

/**
 * @Author: 小海
 * @Date： 2019/07/27 15:09
 * @Description：
 */
@Repository
public interface MoodRepository extends JpaRepository<Mood,Integer> {
}
