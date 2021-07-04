package cn.celess.house.entity.dto;

import cn.celess.house.entity.BaseEntity;
import cn.celess.house.entity.vo.BaseVO;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @author: 小海
 * @date： 2021/06/05 14:51
 * @description：
 */
public interface BaseDTO<T extends BaseEntity<T, ?>> {
    /**
     * trans this instance to a entity instance
     *
     * @return the instance of a entity
     */
    public T toEntity();

    /**
     * copy properties from dto to entity
     *
     * @param dto    dto instance
     * @param entity entity instance
     * @return entity instance
     */
    default T beanCopy(BaseDTO<T> dto, T entity) {
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }
}
