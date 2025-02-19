package com.mty.landmg.common;

import com.mty.landmg.common.enums.ResultCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusinessException extends RuntimeException {
    private int code;
    private String msg;

    public BusinessException() {
    }

    public BusinessException(ResultCode returnCode) {
        this(returnCode.getCode(),returnCode.getMessage());
    }

    public BusinessException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }
}
