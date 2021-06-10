package cn.celess.house.entity;

import cn.celess.house.entity.vo.BaseVO;
import org.springframework.beans.BeanUtils;

/**
 * @author: 小海
 * @date： 2021/06/05 11:34
 * @description：
 */
public abstract class BaseEntity<T, ID> {
    /**
     * 返回主键值
     *
     * @return 主键值
     */
    public abstract ID getPrimaryKey();

    public BaseVO<?> toViewObject() {
        return null;
    }

    protected  final <VO extends BaseVO<VO>> VO beanCopy(T entity, VO vo) {
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }
}
