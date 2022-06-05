package com.atsun.dormitory.enums;

import lombok.Getter;

/**
 * @author SH
 */

@Getter
public enum TransCode {

    SUCCESS("SUCCESS", "成功"),
    SYSTEM_ERROR("SYSTEM_ERROR", "系统错误"),
    CUSTOM_EXCEPTION_MSG("CUSTOM_EXCEPTION_MSG", ""),
    SYSTEM_CONFIG_ERROR("SYSTEM_CONFIG_ERROR", "系统配置错误"),
    SYSTEM_NO_CONFIG("SYSTEM_NO_CONFIG", "系统未配置"),
    SERVICE_EXCEPTION("SERVICE_EXCEPTION", "服务异常"),
    SERVICE_BUSYNESS("SERVICE_BUSYNESS", "服务繁忙, 请稍后再试"),
    NOT_SUPPORTED("NOT_SUPPORTED", "系统暂时不支持"),
    REQ_METHOD_NOT_SUPPORTED("REQ_METHOD_NOT_SUPPORTED", "请求方法不支持"),
    SCHEDULE_JOB_EXECUTE_ERROR("SCHEDULE_JOB_EXECUTE_ERROR", "调度任务执行失败"),
    ILLEGAL_ARGUMENT_EXCEPTION("ILLEGAL_ARGUMENT_EXCEPTION", "参数异常"),
    FORBIDDEN("FORBIDDEN", "禁止访问"),
    CHECK_SIGN_FAIL("CHECK_SIGN_FAIL", "签名验签失败"),
    CODE_EXIST("CODE_EXIST", "编码已存在"),
    RECORD_EXIST("RECORD_EXIST", "数据已存在"),
    RECORD_NOT_EXIST("RECORD_NOT_EXIST", "查询数据不存在"),
    SQL_INTEGRITY_CONSTRAINT_VIOLATION("SQL_INTEGRITY_CONSTRAINT_VIOLATION", "操作失败，可能出现以下情况【1.删除被其它数据关联的数据；2.违反了数据的唯一约束(出现重复数据)；3.关联的数据不存在；】"),
    ATT_NOT_EXIST("ATT_NOT_EXIST", "上传附件不存在"),
    TASK_QUEUE_FULL_REJECTED("TASK_QUEUE_FULL_REJECTED", "任务队列已满, 请稍后再试"),

    ACCOUNT_REQUIRE_LOGIN("ACCOUNT_REQUIRE_LOGIN", "账户需要登录"),
    ACCOUNT_REQUIRE_SET_PASSWORD("ACCOUNT_REQUIRE_SET_PASSWORD", "账户需要设置密码"),
    ACCOUNT_REQUIRE_BIND("ACCOUNT_REQUIRE_BIND", "账户需要绑定"),
    ACCOUNT_REQUIRE_REGISTERED("ACCOUNT_REQUIRE_REGISTERED", "账户需要注册"),
    ACCOUNT_NOT_EXIST("ACCOUNT_NOT_EXIST", "账户信息不存在"),
    PHONE_NO_REGISTERED("PHONE_NO_REGISTERED", "手机号码已经被注册, 可直接登录账号"),
    PASSWORD_NOT_EQUALLY("PASSWORD_NOT_EQUALLY", "两次密码不一致, 请检查后提交"),
    PASSWORD_FORMAT_ERROR("PASSWORD_FORMAT_ERROR", "密码格式错误"),
    ACCOUNT_LOGIN_EXCEPTION("ACCOUNT_LOGIN_EXCEPTION", "账户登录异常"),
    ACCOUNT_BAD_CREDENTIALS("ACCOUNT_BAD_CREDENTIALS", "用户名或密码不正确"),
    VERIFY_CODE_EXPIRE("VERIFY_CODE_EXPIRE", "验证码已过期"),
    VERIFY_CODE_ERROR("VERIFY_CODE_ERROR", "验证码不正确"),
    ACCOUNT_LOCKED("ACCOUNT_LOCKED", "账户已锁定"),
    ACCOUNT_DISABLED("ACCOUNT_DISABLED", "账户未启用"),
    ACCOUNT_EXPIRED("ACCOUNT_EXPIRED", "账户已过期"),

    INTERFACE_NO_DATA_RESPONSE("INTERFACE_NO_DATA_RESPONSE", "第三方接口无数据响应"),
    INVALID_TOKEN("INVALID_TOKEN", "无效Token"),
    INTERFACE_ERROR("INTERFACE_ERROR", "接口调用异常"),
    RETURN_CODE_FAIL("RETURN_CODE_FAIL", "接口响应失败"),
    RESULT_CODE_FAIL("RESULT_CODE_FAIL", "接口响应结果失败"),
    INTERFACE_DATA_SIGN_ERROR("INTERFACE_DATA_SIGN_ERROR", "第三方接口数据签名错误"),

    AUTHORIZATION_FAILED("AUTHORIZATION_FAILED", "授权失败"),

    FILE_UPLOAD_LIMIT("FILE_UPLOAD_LIMIT", "文件限制"),
    FILE_KEY_EXIST("FILE_KEY_EXIST", "文件唯一标识已存在"),
    NULL_FILE("NULL_FILE", "空文件"),
    FILE_NOT_EXIST("FILE_NOT_EXIST", "文件不存在"),

    SMS_SEND_FAIL("SMS_SEND_FAIL", "短信发送失败"),

    PAY_MODE_NOT_EXIST("PAY_MODE_NOT_EXIST", "支付方式不存在"),
    PAY_MODE_CLOSED("PAY_MODE_CLOSED", "支付方式已关闭");

    private final String code;

    private final String msg;

    TransCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static TransCode getEnum(String tag) {
        try {
            return valueOf(tag.toUpperCase());
        } catch (Exception ignore) {
        }

        return SYSTEM_ERROR;
    }

}
