package site.celess.house.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import site.celess.house.entity.Response;
import site.celess.house.enumpac.ResponseEnum;
import site.celess.house.util.ResponseUtil;

/**
 * @Author: 小海
 * @Date： 2019/08/02 21:12
 * @Description：
 */
@ControllerAdvice
public class HouseExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Response excHandler(Exception e) {
        if (e instanceof ResponseException) {
            return new Response(((ResponseException) e).getCode(), ((ResponseException) e).getMsg(), null);
        }
        return null;
    }
}
