package codesquad.security;

import codesquad.aspect.CustomAnnotation;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.lang.reflect.Method;

import static org.slf4j.LoggerFactory.getLogger;

public class BasicInterceptor extends HandlerInterceptorAdapter {
    private static final Logger log = getLogger(BasicInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.debug("------ BasicInterceptor ------");

        if(handler instanceof HandlerMethod) {
            HandlerMethod hm = (HandlerMethod) handler;
            Method method = hm.getMethod();
            log.debug("method name : " + method.getName());
            if (method.getDeclaringClass().isAnnotationPresent(Controller.class)) {
                if (method.isAnnotationPresent(CustomAnnotation.class)) {
                    System.out.println(method.getAnnotation(CustomAnnotation.class).value());
                }
            }
        }
        return true;
    }
}
