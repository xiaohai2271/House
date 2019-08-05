package site.celess.house.enumpac;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: 小海
 * @Date： 2019/08/02 21:21
 * @Description： 数据响应状态码
 */
@Getter
@AllArgsConstructor
public enum ResponseEnum {
    // 1xxx 错误 各种exception错误
    SUCCESS(0, "成功"),
    FAILURE(-1, "失败"),
    ERROR(1001, "系统错误，已记录！"),

    // 1100 -1900 为自定义的exception的响应状态

    // 1xxx 为参数问题
    PARAMETER_ILLEGAL(1001, "数据非法"),
    PARAMETER_ERROR(1002, "参数填写有误"),
    PARAMETER_ID_NOT_NULL(1003, "id不能为空"),

    // 2xxx数据问题
    DATA_NOT_EXIST(2001, "数据不存在"),
    DATA_HAS_EXIST(2002, "数据已存在"),
    TODOCATEGORY_NOT_EXIST(2101, "分类不存在"),
    TODOCATEGORY_HAS_EXIST(2102, "分类已存在"),
    TODO_NOT_EXIST(2201, "Todo项不存在"),
    TODO_HAS_EXIST(2202, "Todo项已存在");

    private int code;
    private String msg;
}
