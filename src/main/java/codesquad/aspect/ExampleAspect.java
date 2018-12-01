package codesquad.aspect;


import codesquad.question.Question;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.Arrays;

import static org.slf4j.LoggerFactory.getLogger;

@Aspect
@Component
public class ExampleAspect {
    private static final Logger log = getLogger(ExampleAspect.class);

//    @Around("@annotation(CustomAnnotation) && args(id, model)")
//    public Object logExecutionTime(ProceedingJoinPoint joinPoint, Long id, Model model) throws Throwable {
//        final long start = System.currentTimeMillis();
//        log.error("아이디 : " + id.toString());
//        final Object proceed = joinPoint.proceed();
//        final long executionTime = System.currentTimeMillis() - start;
//        log.error(joinPoint.getSignature() + " executed in " + executionTime + "ms");
//        return proceed;
//    }

//    @Before("@annotation(CustomAnnotation) && args(question)")
//    public void logExecutionTime(Question question) {
//        log.error("질문 : " + question.toString());
//    }

//    @Around("@annotation(CustomAnnotation) && args(result, question)")
//    public Object logExecutionTime(ProceedingJoinPoint jp, Result result, Question question) throws Throwable {
//        log.error("질문 : " + question.toString());
//        result = Result.fail("로그인에 실패하셨습니다");  // Result에는 default 생성자가 있어야 합니다.
//        Object[] resultObj = {result, question};  // Target메서드에 있던 매개변수와 같은 개수로 담아 보내는 것이 포인트!!
//        return jp.proceed(resultObj);
//    }

//    @Around("execution(* codesquad.question.QuestionController.*(..)) || execution(* codesquad.user.UserController.*(..))")
//    public Object logExecutionTime(ProceedingJoinPoint jp) throws Throwable {
//        Object[] objects = jp.getArgs();
//        Question question = (Question)Arrays.stream(objects).filter(object -> object instanceof Question).findFirst().orElse(null);
//        Long id = (Long)Arrays.stream(objects).filter(object -> object instanceof Long).findFirst().orElse(null);
//        if(question == null) {
//            log.info("---------------redirect요청---------------");
//            return "redirect:/";  // redirect 적용가능
//        }
//        if(id != null) {
//            System.out.println("아이디 : " + id);
//        }
//        return jp.proceed(objects);
//    }


    @Pointcut("execution(* codesquad.question.QuestionController.*(..)) && args(question,..)")
    public void test1(Question question){
}

    @Pointcut("execution(* codesquad.user.UserController.*(..)) && args(question,..)")
    public void test2(Question question) {
    }

//    @Pointcut (value = ".., args(codesquad.question.Question, ..)", argNames = "question")
    @Around("test1(question) || test2(question)")
    public Object logExecutionTime(ProceedingJoinPoint jp, Question question) throws Throwable {
        Object[] objects = jp.getArgs();
        log.info("---------------aop호출---------------");
//        Question question = (Question) Arrays.stream(objects).filter(object -> object instanceof Question).findFirst().orElse(null);
        Long id = (Long) Arrays.stream(objects).filter(object -> object instanceof Long).findFirst().orElse(null);
        if (question == null) {
            log.info("---------------redirect요청---------------");
            return "redirect:/";  // redirect 적용가능
        }
        if (id != null) {
            System.out.println("아이디 : " + id);
        }
        return jp.proceed(objects);
    }
}

