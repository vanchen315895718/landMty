package com.mty.landmg.common.enums;

import com.mty.landmg.common.api.IResultCode;

/**
 * 返回给前台的状态码和对应提示
 *
 * @author york
 * @since 2025-02-18
 */
@SuppressWarnings("unused")
public enum ResultCode implements IResultCode {
    /**
     * 操作成功
     */
    SUCCESS(200, "操作成功"),

    /**
     * 业务异常
     */
    FAILURE(400, "业务异常"),

    /**
     * 请求未授权
     */
    UN_AUTHORIZED(401, "请求未授权"),

    /**
     * 404 没找到请求
     */
    NOT_FOUND(404, "404 没找到请求"),

    /**
     * 消息不能读取
     */
    MSG_NOT_READABLE(400, "消息不能读取"),

    /**
     * 不支持当前请求方法
     */
    METHOD_NOT_SUPPORTED(405, "不支持当前请求方法"),

    /**
     * 不支持当前媒体类型
     */
    MEDIA_TYPE_NOT_SUPPORTED(415, "不支持当前媒体类型"),

    /**
     * 请求被拒绝
     */
    REQ_REJECT(403, "请求被拒绝"),

    /**
     * 服务器异常
     */
    INTERNAL_SERVER_ERROR(500, "服务器异常"),

    /**
     * 参数校验失败
     */
    PARAM_VALID_ERROR(400, "参数校验失败"),

    /***
     * 认证错误
     */
    AUTHCATION_ERROR(10001,"用户认证异常");



    private final int code;

    private final String message;

    ResultCode(final int code, final String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

}
