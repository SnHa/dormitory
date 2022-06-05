package com.atsun.dormitory.exception;

import com.atsun.dormitory.enums.TransCode;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;


/**
 * @author SH
 */
@Getter
public class TransException extends Exception {

    private static final long serialVersionUID = -6245961810717298348L;

    private TransCode transCode;

    private String errCode;

    private Object data;

    public TransException(TransCode transCode) {
        super(transCode.getMsg());
        this.transCode = transCode;
    }

    public TransException(TransCode transCode, String msg) {
        this(transCode, null, msg);
    }

    public TransException(TransCode transCode, String errCode, String msg) {
        this(transCode, errCode, msg, null);
    }

    public TransException(TransCode transCode, Object data) {
        this(transCode, null, data);
    }

    public TransException(TransCode transCode, String msg, Object data) {
        this(transCode, null, msg, data);
    }

    public TransException(TransCode transCode, String errCode, String msg, Object data) {
        super(StringUtils.defaultString(msg, transCode.getMsg()));
        this.transCode = transCode;
        this.errCode = errCode;
        this.data = data;
    }

}
