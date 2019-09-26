package com.vic.base;

import javax.servlet.http.HttpServletRequest;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice(basePackages={"com.vic.ck.console"})
public class GlobalExceptionHandler {

    public static final String DEFAULT_ERROR_VIEW = "500";

    @ExceptionHandler
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception ex) throws Exception {
        ModelAndView mav = createModelAndView(req, ex);
        mav.setViewName(DEFAULT_ERROR_VIEW);
        return mav;
    }

    private ModelAndView createModelAndView(HttpServletRequest req, Exception ex) throws Exception {
        if (AnnotationUtils.findAnnotation(ex.getClass(), ResponseStatus.class) != null) {
            throw ex;
        }

        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", ex);
        mav.addObject("url", req.getRequestURL());
        return mav;
    }
}
