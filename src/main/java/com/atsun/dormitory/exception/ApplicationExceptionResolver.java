package com.atsun.dormitory.exception;

import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.util.ArrayUtil;
import com.atsun.dormitory.enums.TransCode;
import com.atsun.dormitory.vo.DataResponse;
import com.atsun.dormitory.vo.NoDataResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

/**
 * @author SH
 */
@Slf4j
@Component
@RestControllerAdvice
public class ApplicationExceptionResolver {

    @ExceptionHandler(value = {HttpRequestMethodNotSupportedException.class})
    public ResponseEntity<NoDataResponse> handleException(HttpRequestMethodNotSupportedException e) {

        String[] supportedMethods = e.getSupportedMethods();

        return new ResponseEntity<>(
                new NoDataResponse(
                        false,
                        TransCode.REQ_METHOD_NOT_SUPPORTED.getCode(),
                        "不支持".concat(e.getMethod())
                                .concat("请求方式, 只支持")
                                .concat(ArrayUtil.join(supportedMethods, ", "))
                ), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<NoDataResponse> handleException(MethodArgumentNotValidException e) {

        List<ObjectError> errors = e.getBindingResult().getAllErrors();

        return new ResponseEntity<>(
                new NoDataResponse(
                        false,
                        TransCode.ILLEGAL_ARGUMENT_EXCEPTION.getCode(),
                        errors.isEmpty() ? TransCode.ILLEGAL_ARGUMENT_EXCEPTION.getMsg() : errors.get(0).getDefaultMessage()
                ), HttpStatus.OK);
    }

    @ExceptionHandler(value = {Throwable.class})
    public ResponseEntity<NoDataResponse> handleException(Throwable e) {

        NoDataResponse resp;

        if (e instanceof TransException) {
            TransException t = (TransException) e;
            resp = new DataResponse<>(
                    false, t.getTransCode().getCode(), t.getTransCode().getMsg(), t.getErrCode(), e.getMessage(), ((TransException) e).getData()
            );
        } else {
            Throwable rootCause = ExceptionUtil.getRootCause(e);

            log.error(String.format("Catch exception: %s",
                    null == rootCause ? "" : StringUtils.defaultString(rootCause.getMessage(), "none")
            ), rootCause);

            if (rootCause instanceof SQLIntegrityConstraintViolationException) {
                resp = new NoDataResponse(
                        false,
                        TransCode.SQL_INTEGRITY_CONSTRAINT_VIOLATION.getCode(),
                        TransCode.SQL_INTEGRITY_CONSTRAINT_VIOLATION.getMsg()
                );
            } else {
                resp = new NoDataResponse(false, TransCode.SYSTEM_ERROR.getCode(), TransCode.SYSTEM_ERROR.getMsg());
            }
        }

        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

}
