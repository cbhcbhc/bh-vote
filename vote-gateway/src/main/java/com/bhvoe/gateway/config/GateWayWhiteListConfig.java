package com.bhvoe.gateway.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 白名单
 */
@Component
@Data
@RefreshScope
public class GateWayWhiteListConfig {
    @Value("#{'${gateway.whitelist.paths}'.split(',')}")
    private List<String> whitepathList;

    /**
     * 判断指定的路径是否在白名单中
     *
     * @param path 请求的路径
     * @return 是否在白名单中
     */
    public boolean isPathWhiteListed(String path) {
        return whitepathList.contains(path);
    }
}
