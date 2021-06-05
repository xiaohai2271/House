package cn.celess.house.service;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: 小海
 * @date： 2021/06/05 00:42
 * @description：
 */
public interface IBaseService<T, IDTYPE> {
    /**
     * 新增数据
     *
     * @param t 数据
     * @return 新增后的数据
     */
    T insert(T t);

    /**
     * 删除数据
     *
     * @param id 数据id
     * @return 是否成功
     */
    boolean remove(IDTYPE id);

    /**
     * 更新数据
     *
     * @param t 数据
     * @return 更新后的数据
     */
    T update(T t);

    /**
     * 查询数据
     *
     * @param id id
     * @return 数据
     */
    T queryById(IDTYPE id);

    /**
     * 查询数据
     *
     * @return 所有数据
     */
    List<T> queryAll();
}
