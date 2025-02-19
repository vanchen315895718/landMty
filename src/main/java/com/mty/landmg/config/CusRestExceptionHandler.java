package com.mty.landmg.config;

import com.mty.landmg.common.BusinessException;
import com.mty.landmg.common.api.R;
import com.mty.landmg.common.enums.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@ResponseBody
public class CusRestExceptionHandler {

    /**
     * 处理自定义异常
     *
     * @param e BusinessException
     * @return
     */
    @ExceptionHandler(BusinessException.class)
    public R<String> businessException(BusinessException e) {
        log.error("业务异常 code={}, BusinessException = {}", e.getCode(), e.getMessage(), e);
        return R.fail(e.getCode(), e.getMsg());
    }

    /**
     * 处理空指针的异常
     *
     * @param e NullPointerException
     * @return
     * @description 空指针异常定义为前端传参错误，返回400
     */
    @ExceptionHandler(NullPointerException.class)
    public R<String> nullPointerException(NullPointerException e) {
        log.error("空指针异常 NullPointerException ", e);
        return R.fail(ResultCode.INTERNAL_SERVER_ERROR, "nullPointerException");
    }

    /**
     * 认证异常
     * @param e
     * @return
     */
    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public R<String> authenticationServiceException(InternalAuthenticationServiceException e) {
        log.error("认证异常 InternalAuthenticationServiceException ", e);
        return R.fail(ResultCode.AUTHCATION_ERROR);
    }

    /**
     * 处理其他异常
     *
     * @param e otherException
     * @return
     */
    @ExceptionHandler(Exception.class)
    public R<String> exception(Exception e) {
        log.error("未知异常 exception = {}", e.getMessage(), e);
        return R.fail(ResultCode.INTERNAL_SERVER_ERROR, "未知异常，请联系管理员");
    }
}
