package com.bhvoe.gateway.filter;

//import com.msmall.gateway.config.GateWayWhiteListConfig;
import com.alibaba.fastjson.JSONObject;
import com.bhvoe.gateway.config.GateWayWhiteListConfig;
import com.bhvoe.gateway.constant.GatewayConstant;
import com.bhvote.redis.service.RedisService;
import enums.AppHttpCodeEnum;
import exception.SystemException;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import utils.JwtUtil;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;

/**
 * 认证全局过滤器
 */
//@Component
@Slf4j
public class AuthFilter implements GlobalFilter, Ordered {
    @Resource
    private GateWayWhiteListConfig gateWayWhiteListConfig;
    @Resource
    private RedisService redisService;

    /**
     * 请求头
     */
    private static final String AUTHORIZATION_HEADER = GatewayConstant.HEADER;
    /**
     * Bearer
     */
    private static final String BEARER_PREFIX = GatewayConstant.BEARER;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        HttpHeaders headers = request.getHeaders();

        // 获取请求的路径和服务名
        String requestPath = request.getURI().getPath();

        // 判断当前请求是否需要放行
        if (gateWayWhiteListConfig.isPathWhiteListed(requestPath)) {
            // 如果请求路径在白名单中，直接放行
            return chain.filter(exchange);
        }


        // 从请求头中获取JWT token
        String authorizationHeader = headers.getFirst(AUTHORIZATION_HEADER);
        if (authorizationHeader == null || authorizationHeader == "" || !authorizationHeader.startsWith(BEARER_PREFIX)) {
             //如果请求头中没有Authorization字段，或者字段值不以Bearer开头，直接返回鉴权失败
            log.error("token验证失败或已过期...");
            return writeResponse(exchange.getResponse(),401,"token验证失败或已过期...请重新登录");

        }

        //获取token的值,并访问redis进行校验
        String token = authorizationHeader.substring(BEARER_PREFIX.length());

        // 解析token
        Claims claims = null;
        try {
            claims = JwtUtil.parseJWT(token);
        } catch (Exception e) {
            throw new SystemException(AppHttpCodeEnum.TOKEN_ERROR);
        }
        //获取账户id
        String userId = claims.getSubject();
        String tokenKey = GatewayConstant.REDISTOKEN+ userId;
        //进行校验
        if (userId!= null && redisService.getCacheObject(tokenKey) == token){
            // 将token放入请求头中，传递给下游服务
            ServerHttpRequest modifiedRequest = request.mutate().header(AUTHORIZATION_HEADER, BEARER_PREFIX + token).build();
            return chain.filter(exchange.mutate().request(modifiedRequest).build());
        }

        // 如果token无效，返回鉴权失败
        log.error("token非法...");
        return writeResponse(exchange.getResponse(),503,"token非法...");
    }

    /**
     * 构建返回内容
     *
     * @param response ServerHttpResponse
     * @param code     返回码
     * @param msg     返回数据
     * @return Mono
     */
    protected Mono<Void> writeResponse(ServerHttpResponse response, Integer code, String msg) {
        JSONObject message = new JSONObject();
        message.put("code", code);
        message.put("msg", msg);
        byte[] bits = message.toJSONString().getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = response.bufferFactory().wrap(bits);
        response.setStatusCode(HttpStatus.OK);
        // 指定编码，否则在浏览器中会中文乱码
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        return response.writeWith(Mono.just(buffer));
    }

    @Override
    public int getOrder() {
        return -10;
    }
}
