package cn.celess.house.entity;

import lombok.Data;
import cn.celess.house.enums.ResponseEnum;

/**
 * @Author: 小海
 * @Date： 2019/08/02 21:16
 * @Description： 响应数据
 */
@Data
public class Response<T> {
    private int code;
    private String msg;
    private T data;

    public Response() {
    }

    public Response(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Response(ResponseEnum enu, T data) {
        this.code = enu.getCode();
        this.msg = enu.getMsg();
        this.data = data;
    }
}
