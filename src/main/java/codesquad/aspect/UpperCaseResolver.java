package codesquad.aspect;

import org.slf4j.Logger;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import static org.slf4j.LoggerFactory.getLogger;

public class UpperCaseResolver implements HandlerMethodArgumentResolver {
    private static final Logger log = getLogger(UpperCaseResolver.class);

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        log.debug("========= supportsParameter 호출 =========");
        return parameter.getParameterAnnotation(UpperCase.class) != null;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        log.debug("========= resolveArgument 호출 =========");
        UpperCase attr = parameter.getParameterAnnotation(UpperCase.class);
        return webRequest.getParameter(attr.value()).toUpperCase();
    }
}
