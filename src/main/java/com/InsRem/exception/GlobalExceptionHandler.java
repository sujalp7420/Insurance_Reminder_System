package com.InsRem.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(
            Exception ex) {

        ModelAndView mv =
                new ModelAndView();

        mv.addObject(
                "message",
                ex.getMessage());

        mv.setViewName("error");

        return mv;
    }
}