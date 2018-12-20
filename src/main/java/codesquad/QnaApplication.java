package codesquad;

import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import static org.slf4j.LoggerFactory.getLogger;

@SpringBootApplication
@ComponentScan({"codesquad"})
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class QnaApplication {
    private static final Logger log = getLogger(QnaApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(QnaApplication.class, args);
    }
}
