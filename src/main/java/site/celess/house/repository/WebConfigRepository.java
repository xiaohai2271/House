package site.celess.house.repository;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import site.celess.house.entity.WebConfig;

import javax.transaction.Transactional;

/**
 * @Author: 小海
 * @Date： 2019/07/27 16:20
 * @Description：
 */
public interface WebConfigRepository extends JpaRepository<WebConfig, Integer> {

    @Cacheable(cacheNames = "config", key = "#name")
    @Query("select value from WebConfig where name=?1")
    String getValueByName(String name);

    @CacheEvict(value = "config", key = "#name")
    @Query(value = "update WebConfig  set value = ?2 where name=?1")
    @Modifying
    @Transactional(rollbackOn = Exception.class)
    int UpdateValue(String name, String value);
}
