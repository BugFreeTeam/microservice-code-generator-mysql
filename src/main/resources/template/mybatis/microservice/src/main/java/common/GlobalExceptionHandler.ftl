package ${packagePath}.common;

import com.anjuxing.platform.common.base.JsonResult;
import com.anjuxing.platform.common.exception.BaseException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理类
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.addObject("url", req.getRequestURL());
        mav.setViewName("error");
        return mav;
    }

    @ExceptionHandler(value = BaseException.class)
    @ResponseBody
    public JsonResult jsonErrorHandler(HttpServletRequest req, BaseException e) throws Exception {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setResult(JsonResult.FAILURE);
        jsonResult.setCode(e.getCode());
        jsonResult.setMessage(e.getMessage()); //根据异常代码表转成相应的文字
        return jsonResult;
    }

}
