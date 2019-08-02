package site.celess.house.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.celess.house.entity.Mood;

/**
 * @Author: 小海
 * @Date： 2019/07/27 15:09
 * @Description：
 */
public interface MoodRepository extends JpaRepository<Mood,Integer> {
}
