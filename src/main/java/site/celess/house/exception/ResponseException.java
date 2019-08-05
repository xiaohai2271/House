package site.celess.house.exception;

import lombok.Getter;
import site.celess.house.enumpac.ResponseEnum;

/**
 * @Author: 小海
 * @Date： 2019/08/02 21:08
 * @Description： 自定义一个错误 方便返回状态信息
 */
@Getter
public class ResponseException extends RuntimeException {
    private int code;
    private String msg;

    public ResponseException(ResponseEnum enu) {
        this(enu.getCode(), enu.getMsg());
    }

    public ResponseException(int code, String msg) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

}
