package com.mty.landmg.common.api;

import java.io.Serializable;

/**
 * 结果码和消息提示接口
 *
 * @author york
 * @since 2025-02-18
 */
@SuppressWarnings("unused")
public interface IResultCode extends Serializable {

    /**
     * code
     *
     * @return 结果码
     */
    int getCode();

    /**
     * 消息
     *
     * @return 消息提示
     */
    String getMessage();

}
