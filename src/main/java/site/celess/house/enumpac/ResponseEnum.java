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
    ;

    private int code;
    private String msg;
}
