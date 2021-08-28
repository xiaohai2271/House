package cn.celess.house.entity;

import org.springframework.beans.BeanUtils;

/*********************************************************
 * @SYSTEM 票据交易管理平台
 * @MODULE TODO
 * @DESCRIPTION TODO
 * @AUTHOR zhenghai
 * @DATE 2021年08月28日
 * @MODIFY
 * --------------------------------------------------
 * / 修改单号 / 修改人员 / 修改日期 / 评审人员 / 修改说明 
 * --------------------------------------------------
 * /          /          /          /          /          
 * --------------------------------------------------
 *********************************************************/
public interface BaseBean {
    /**
     * copy properties from dto to entity
     *
     * @param src  origin data
     * @param dist destination data
     * @param <S>  origin data object type
     * @param <D>  destination data type
     * @return destination data
     */
    default <S, D> D copy(S src, D dist) {
        BeanUtils.copyProperties(src, dist);
        return dist;
    }
}
