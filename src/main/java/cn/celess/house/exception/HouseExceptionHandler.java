package cn.celess.house.exception;

import cn.celess.house.util.ResponseUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.celess.house.entity.Response;

/**
 * @Author: 小海
 * @Date： 2019/08/02 21:12
 * @Description：
 */
@ControllerAdvice
public class HouseExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Response<Object> excHandler(Exception e) {
        if (e instanceof ResponseException) {
            return ResponseUtil.error((ResponseException) e);
        }
        e.printStackTrace();
        return null;
    }
}
