package cn.celess.house.entity;

import cn.celess.house.entity.vo.BaseVO;
import org.springframework.beans.BeanUtils;

/**
 * @author: 小海
 * @date： 2021/06/05 11:34
 * @description：
 */
public interface BaseEntity<T, ID> {
    /**
     * 返回主键值
     *
     * @return 主键值
     */
    ID getPrimaryKey();

    /**
     * 定义返回成ViewObject的逻辑
     *
     * @return 对应的ViewObject
     */
    <VO extends BaseVO<?>> VO toViewObject();

    default <VO extends BaseVO<?>> VO beanCopy(T entity, VO vo) {
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }
}
