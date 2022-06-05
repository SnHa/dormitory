package com.atsun.dormitory.vo;

import lombok.Getter;
import lombok.Setter;


/**
 * @author SH
 */
@Getter
@Setter
public class DataResponse<T> extends NoDataResponse {

    private static final long serialVersionUID = -7666525385083762575L;

    private T data;

    public DataResponse(boolean success, String transCode, String transDesc, String errCode, String errDesc, T data) {
        super(success, transCode, transDesc, errCode, errDesc);
        this.data = data;
    }

    public DataResponse(boolean success, String transCode, String transDesc, T data) {
        this(success, transCode, transDesc, null, null, data);
    }

    public DataResponse(boolean success, String transCode, String transDesc) {
        this(success, transCode, transDesc, null, null, null);
    }

    public DataResponse(boolean success, String transCode, String transDesc, String errCode, String errDesc) {
        this(success, transCode, transDesc, errCode, errDesc, null);
    }

    public DataResponse() {
        this(true, null, null, null, null, null);
    }

    public DataResponse(T data) {
        this(true, null, null, null, null, data);
    }

}
