package com.zj.common.globalFilter;

import com.zj.sys.dto.TokenUser;
import com.zj.utils.JwtTokenUtil;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
/**
* @discription 统一拦截器
* @author 赵健
* @date 2023/2/2 10:22
* @version 1.0
*/
@Component
public class CustomFilter implements GlobalFilter, Ordered {
    private static final String TOKEN_PREFIX = "AuthorizationToken";

    private static final String[] passList = new String[]{"getVerifyImg", "login"};

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        Boolean flag = true;
        HttpStatus httpStatus = null;
        URI uri = request.getURI();
        if (uri.getPath().contains(passList[0])) {
            return chain.filter(exchange);
        }
        if (!uri.getPath().contains("login")) {
            // header中是否存在token
            String token = request.getHeaders().getFirst(TOKEN_PREFIX);
            // cookie中是否存在token
            if (token == null) {
                HttpCookie cookie = request.getCookies().getFirst(TOKEN_PREFIX);
                if (cookie != null) {
                    token = cookie.getValue();
                }
            }
            // 参数中是否存在token
            if (token == null) {
                token = request.getQueryParams().getFirst(TOKEN_PREFIX);
            }
            if (token == null) {
                flag = false;
                httpStatus = HttpStatus.FORBIDDEN;
            } else {

                String redisKey = null;
                try {
                    redisKey = JwtTokenUtil.getTokenUserFromToken(token);
                } catch (Exception e) {
                    System.err.println("解析异常" + e.getMessage());
                    flag = false;
                    httpStatus = HttpStatus.UNAUTHORIZED;
                }
                if (redisKey != null) {
                    HttpHeaders headers = request.getHeaders();
                    headers= HttpHeaders.writableHttpHeaders(headers);
                    headers.set("sessionRedisId", redisKey);
//                    String newToken = JwtTokenUtil.refreshToken(token);
//                    if (newToken != null) {
//                        response.getHeaders().set("Access-Control-Expose-Headers", "AuthorizationToken");
//                        response.getHeaders().set("AuthorizationToken", newToken);
//                    }
                } else {
                    flag = false;
                    httpStatus = HttpStatus.UNAUTHORIZED;
                }
            }
        }
        if (!flag) {
            response.setStatusCode(httpStatus);
            return response.setComplete();
        }
//        request.getHeaders().set("sessionId",);
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
