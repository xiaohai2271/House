package site.celess.house.exception;

import lombok.Getter;

/**
 * @Author: 小海
 * @Date： 2019/08/02 21:08
 * @Description： 自定义一个错误 方便返回状态信息
 */
@Getter
public class ResponseException extends RuntimeException {
    private int code;
    private String msg;

    public ResponseException(String message, int code, String msg) {
        super(message);
        this.code = code;
        this.msg = msg;
    }
}
