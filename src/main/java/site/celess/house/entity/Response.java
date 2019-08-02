package site.celess.house.entity;

import lombok.Data;
import site.celess.house.enumpac.ResponseEnum;

/**
 * @Author: 小海
 * @Date： 2019/08/02 21:16
 * @Description： 响应数据
 */
@Data
public class Response {
    private int code;
    private String msg;
    private Object data;

    public Response(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Response(ResponseEnum enu, Object data) {
        this.code = enu.getCode();
        this.msg = enu.getMsg();
        this.data = data;
    }
}
