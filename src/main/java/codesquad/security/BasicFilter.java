package codesquad.security;

import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;

@Component
public class BasicFilter implements Filter {
    private static final Logger log = getLogger(BasicFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        log.info("Starting a BasicFilter for req : {}", req.getRequestURI());

        chain.doFilter(request, response);
        log.info("Committing a BasicFilter for req : {}", req.getRequestURI());
    }
}
