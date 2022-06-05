package com.atsun.dormitory.controller;

import com.atsun.dormitory.enums.TransCode;
import com.atsun.dormitory.vo.DataResponse;
import com.atsun.dormitory.vo.NoDataResponse;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author SH
 */
@Api
@RequestMapping(value = BaseController.BASE_URL)
@RestController
public class BaseController {

    public static final String BASE_URL = "api";

    protected NoDataResponse ok() {
        return new NoDataResponse();
    }
    protected NoDataResponse Ok(String transDesc) {
        return new NoDataResponse(true,"SUCCESS",transDesc);
    }

    protected <T> DataResponse<T> ok(T data) {
        return new DataResponse<>(data);
    }

    protected NoDataResponse error(TransCode transCode) {
        return new NoDataResponse(false, transCode.getCode(), transCode.getMsg());
    }

    protected NoDataResponse error() {
        return new NoDataResponse(false, null, null);
    }

    protected NoDataResponse error(TransCode transCode, String transDesc) {
        return new NoDataResponse(false, transCode.getCode(), StringUtils.defaultString(transDesc, transCode.getMsg()));
    }

}
