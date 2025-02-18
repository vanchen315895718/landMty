package com.mty.landmg.common.api;

import java.io.Serializable;

/**
 * 系统错误码，系统异常码定义接口
 *
 * @author york
 * @since 2025-02-18
 */
public interface IErrorCode extends Serializable {

    /**
     * 错误代码
     *
     * @return 结果码
     */
    String getErrorCode();

    /**
     * 错误消息
     *
     * @return 消息提示
     */
    String getErrorMsg();

}
