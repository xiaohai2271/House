package cn.celess.house.enums;

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
    ERROR(10000, "系统错误，已记录！"),

    // 2 A B C D
    // 2 错误总代号  A:模块代号  B:区别是验证异常还是处理异常   CD : 错误码

    // 1xxxx 通用参数问题
    PARAMETER_ILLEGAL(10101, "数据非法"),
    PARAMETER_ERROR(10102, "参数填写有误"),
    PARAMETER_PK_NULL(10103, "id不能为空"),
    PARAMETER_PK_NOT_NULL(10104, "id不为空"),

    // 2xxxx数据问题
    DATA_NOT_EXIST(20001, "数据不存在"),
    DATA_HAS_EXIST(20002, "数据已存在"),

    //21xxx 用户异常
    USER_PASSWORD_MISSING(21101, "登录密码缺失"),
    USER_PASSWORD_ILLEGAL(21102, "密码输入不合法"),

    USER_LOGIN_FAILURE(21901, "登录失败"),
    USER_LOGIN_SUCCESS(21902, "登录成功"),
    USER_HAVE_NOT_LOGIN(21903, "还没登录"),


    // 22xxx TODO_TOPIC 的异常
    TODO_TOPIC_NOT_EXIST(22901, "分类不存在"),
    TODO_TOPIC_HAS_EXIST(22902, "分类已存在"),

    // 23xxx TODO_ITEM 异常
    TODO_ITEM_NOT_EXIST(23901, "Todo项不存在"),
    TODO_ITEM_HAS_EXIST(23902, "Todo项已存在"),


    ;

    private final int code;
    private final String msg;
}
