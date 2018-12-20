package codesquad.security;

import org.slf4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.slf4j.LoggerFactory.getLogger;

public class BasicInterceptor extends HandlerInterceptorAdapter {
    private static final Logger log = getLogger(BasicInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.debug("------ BasicInterceptor ------");
        return true;
    }
}
