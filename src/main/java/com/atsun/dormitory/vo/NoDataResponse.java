package com.atsun.dormitory.vo;

import com.atsun.dormitory.enums.TransCode;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;


/**
 * @author SH
 */
@Getter
@Setter
public class NoDataResponse implements Serializable {

    private static final long serialVersionUID = 8832460930434550146L;

    private boolean success;

    private String transCode;

    private String transDesc;

    private String errCode;

    private String errDesc;

    public NoDataResponse(boolean success, String transCode, String transDesc, String errCode, String errDesc) {
        this.success = success;
        this.transCode = success && StringUtils.isBlank(transCode) ? TransCode.SUCCESS.getCode() : transCode;
        this.transDesc = success && StringUtils.isBlank(transDesc) ? TransCode.SUCCESS.getMsg() : transDesc;
        this.errCode = errCode;
        this.errDesc = errDesc;
    }

    public NoDataResponse(boolean success, String transCode, String transDesc) {
        this(success, transCode, transDesc, null, null);
    }

    public NoDataResponse() {
        this(true, null, null, null, null);
    }

}
