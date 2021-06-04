package cn.celess.house.util;

import cn.celess.house.entity.Response;
import cn.celess.house.enumpac.ResponseEnum;

/**
 * @Author: 小海
 * @Date： 2019/08/02 21:20
 * @Description： 响应工具类
 */
public class ResponseUtil {
    /**
     * 成功的响应
     *
     * @param data 响应的具体数据
     * @return Response
     */
    public static Response success(Object data) {
        return new Response(ResponseEnum.SUCCESS, data);
    }

    /**
     * 失败的响应（带返回值）
     *
     * @return Response
     */
    public static Response failure() {
        return new Response(ResponseEnum.FAILURE, null);
    }

    /**
     * 失败的响应
     *
     * @return Response
     */
    public static Response failure(Object data) {
        return new Response(ResponseEnum.FAILURE, data);
    }

    /**
     * 待状态码的响应 （不带响应数据）
     *
     * @param enu 响应状态
     * @return Response
     */
    public static Response response(ResponseEnum enu) {
        return new Response(enu, null);
    }

    /**
     * 待状态码的响应
     *
     * @param enu  响应状态
     * @param data 响应数据
     * @return Response
     */
    public static Response response(ResponseEnum enu, Object data) {
        return new Response(enu, data);
    }
}
