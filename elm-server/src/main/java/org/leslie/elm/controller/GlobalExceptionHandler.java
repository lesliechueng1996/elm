package org.leslie.elm.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.leslie.elm.dto.ErrorResult;
import org.leslie.elm.exception.ElmParamsException;
import org.leslie.elm.exception.ElmServerException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author zhang
 * date created in 2023/3/17 00:59
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler({ElmParamsException.class, MissingServletRequestParameterException.class})
    public ErrorResult paramExceptionHandler(HttpServletRequest req, HttpServletResponse res, Exception e) {
        log.error("param exception, url: {} >>>", req.getPathInfo(), e);
        res.setStatus(HttpStatus.BAD_REQUEST.value());
        return new ErrorResult(e.getMessage());
    }

    @ExceptionHandler(ElmServerException.class)
    public ErrorResult serverExceptionHandler(HttpServletRequest req, HttpServletResponse res, Exception e) {
        log.error("server exception, url: {} >>>", req.getPathInfo(), e);
        res.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ErrorResult(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ErrorResult exceptionHandler(HttpServletRequest req, HttpServletResponse res, Exception e) {
        log.error("exception, url: {} >>>", req.getPathInfo(), e);
        res.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ErrorResult("系统异常");
    }
}
