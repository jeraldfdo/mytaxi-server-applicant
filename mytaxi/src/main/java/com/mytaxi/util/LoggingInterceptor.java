package com.mytaxi.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * Logger for the application
 *
 * @author jeraldfdo
 */
@Slf4j
public class LoggingInterceptor extends HandlerInterceptorAdapter {

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        StringBuilder logMessage = new StringBuilder();
        logMessage.append("method: ").append(request.getMethod()).append("\t");
        logMessage.append("uri: ").append(request.getRequestURI()).append("\t");
        logMessage.append("status: ").append(response.getStatus()).append("\t");
        logMessage.append("remoteAddress: ").append(request.getRemoteAddr()).append("\t");

        if (ex != null) {
            log.error(logMessage.toString(), ex);
        } else {
            log.info(logMessage.toString());
        }
    }

}
