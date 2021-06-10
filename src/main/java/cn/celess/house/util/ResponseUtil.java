package cn.celess.house.util;

import cn.celess.house.entity.Response;
import cn.celess.house.entity.vo.BaseVO;
import cn.celess.house.enums.ResponseEnum;
import cn.celess.house.exception.ResponseException;

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
    public static <T extends BaseVO<T>> Response<T> success(T data) {
        return new Response<T>(ResponseEnum.SUCCESS, data);
    }

    /**
     * 成功的响应
     *
     * @param data 响应的具体数据
     * @return Response
     */
    public static <T> Response<T> success(T data) {
        return new Response<T>(ResponseEnum.SUCCESS, data);
    }

    /**
     * 失败的响应（带返回值）
     *
     * @return Response
     */
    public static <T> Response<T> failure() {
        return new Response<T>(ResponseEnum.FAILURE, null);
    }

    /**
     * 失败的响应
     *
     * @return Response
     */
    public static <T> Response<T> failure(T data) {
        return new Response<T>(ResponseEnum.FAILURE, data);
    }

    /**
     * 待状态码的响应 （不带响应数据）
     *
     * @param enu 响应状态
     * @return Response
     */
    public static <T> Response<T> response(ResponseEnum enu) {
        return new Response<T>(enu, null);
    }

    /**
     * 待状态码的响应
     *
     * @param enu  响应状态
     * @param data 响应数据
     * @return Response
     */
    public static <T> Response<T> response(ResponseEnum enu, T data) {
        return new Response<T>(enu, data);
    }

    /**
     * 待状态码的响应
     *
     * @param ex   异常
     * @return Response
     */
    public static <T> Response<T> error(ResponseException ex) {
        return new Response<T>(ex.getCode(), ex.getMsg(), null);
    }

    /**
     * 待状态码的响应
     *
     * @param ex   异常
     * @param data 响应数据
     * @return Response
     */
    public static <T> Response<T> error(ResponseException ex, T data) {
        return new Response<T>(ex.getCode(), ex.getMsg(), data);
    }
}
