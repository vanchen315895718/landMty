package com.mty.landmg.common.enums;

import com.mty.landmg.common.api.IErrorCode;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统错误枚举
 *
 * @author york
 * @since 2025-02-18
 */
@AllArgsConstructor
@SuppressWarnings("unused")
public enum SysErrorEnum implements IErrorCode {

    /**
     * 系统配置值查询失败
     */
    SYS_CONFIG_VALUE_QUERY_ERROR("SYS1001001", "系统配置值查询失败"),

    /**
     * 发送MQ消息异常
     */
    SEND_MQ_MSG_ERROR("SYS1001002", "发送MQ消息异常"),

    ;

    /**
     * 异常编码
     */
    private final String errorCode;

    /**
     * 错误提示
     */
    private final String errorMsg;

    @Override
    public String getErrorCode() {
        return this.errorCode;
    }

    @Override
    public String getErrorMsg() {
        return this.errorMsg;
    }

    /**
     * 返回该枚举所有数据
     *
     * @return 枚举列表
     */
    public static List<Map<String, String>> toList() {
        List<Map<String, String>> list = new ArrayList<>();
        for (SysErrorEnum item : SysErrorEnum.values()) {
            Map<String, String> map = new HashMap<>();
            map.put("errorCode", item.getErrorCode());
            map.put("errorMsg", item.getErrorMsg());
            list.add(map);
        }
        return list;
    }

    /**
     * 返回该枚举的map对象
     *
     * @return map
     */
    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<>();
        map.put("errorCode", this.errorCode);
        map.put("errorMsg", this.errorMsg);
        return map;
    }

}
