package cn.celess.house.service;

import cn.celess.house.entity.BaseEntity;
import cn.celess.house.entity.dto.BaseDTO;
import cn.celess.house.entity.vo.BaseVO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author: 小海
 * @date： 2021/06/05 00:42
 * @description：
 */
public interface IBaseService<E extends BaseEntity<E, IDTYPE>, IDTYPE, VO, DTO> {
    /**
     * 新增数据
     *
     * @param t 数据
     * @return 新增后的数据
     */
    VO insert(DTO t);

    /**
     * 删除数据
     *
     * @param id 数据id
     * @return 是否成功
     */
    boolean remove(IDTYPE id);

    /**
     * 删除数据
     *
     * @param ids 数据id集
     * @return 是否成功
     */
    boolean remove(IDTYPE[] ids);

    /**
     * 更新数据
     *
     * @param t 数据
     * @return 更新后的数据
     */
    VO update(DTO t);

    /**
     * 查询数据
     *
     * @param id id
     * @return 数据
     */
    VO queryById(IDTYPE id);

    /**
     * 查询数据
     *
     * @return 所有数据
     */
    List<VO> queryAll();
}
