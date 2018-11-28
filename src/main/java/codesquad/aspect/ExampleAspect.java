package codesquad.aspect;


import codesquad.question.Question;
import codesquad.question.Result;
import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Aspect
@Component
@Controller
public class ExampleAspect {
    private static final Logger log = LoggerFactory.getLogger(ExampleAspect.class);

    @Around("@annotation(LogExecutionTime) && args(id, model)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint, Long id, Model model) throws Throwable {
        final long start = System.currentTimeMillis();
        log.error("아이디 : " + id.toString());
        final Object proceed = joinPoint.proceed();
        final long executionTime = System.currentTimeMillis() - start;
        log.error(joinPoint.getSignature() + " executed in " + executionTime + "ms");
        return proceed;
    }

//    @Before("@annotation(LogExecutionTime) && args(question)")
//    public void logExecutionTime(Question question) {
//        log.error("질문 : " + question.toString());
//    }

    @Around("@annotation(LogExecutionTime) && args(question)")
    public Object logExecutionTime(ProceedingJoinPoint jp, Question question) throws Throwable {
        log.error("질문 : " + question.toString());
        question.setWriter("브래다다다아아아");
        Object[] resultObj = {question};
        return jp.proceed(resultObj);
    }
}

