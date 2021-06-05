package cn.celess.house.entity.dto;

import cn.celess.house.entity.BaseEntity;
import cn.celess.house.entity.vo.BaseVO;
import lombok.Data;

/**
 * @author: 小海
 * @date： 2021/06/05 14:51
 * @description：
 */
@Data
public class BaseDTO<T extends BaseEntity<T, ?>> {
    public T toDataTransferObject() {
        return null;
    }
}
