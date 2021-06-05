package cn.celess.house.entity;

/**
 * @author: 小海
 * @date： 2021/06/05 11:34
 * @description：
 */
public abstract class BaseEntity<ID> {
    /**
     * 返回主键值
     *
     * @return 主键值
     */
    public abstract ID getPrimaryKey();
}
