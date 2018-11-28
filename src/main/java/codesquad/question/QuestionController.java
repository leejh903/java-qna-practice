package codesquad.question;

import codesquad.aspect.LogExecutionTime;
import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.websocket.server.PathParam;
import java.security.Signature;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/questions")
public class QuestionController {
    @Autowired
    private QuestionRepository questionRepository;

    @PostMapping("")
    @LogExecutionTime
    public String createQuestion(Question question) throws Throwable {
        question.setTime(createTime());
        System.out.println("안녕");
        System.out.println(question.toString());
        questionRepository.save(question);
        return "redirect:/";
    }

    @GetMapping("/{id}")
    @LogExecutionTime
    public String showQuestion(@PathVariable Long id, Model model) {
        Question question = findQuestion(id);
        model.addAttribute("question", question);
        return "qna/show";
    }

    private Question findQuestion(Long id) {
        return questionRepository.findById(id).orElseThrow(() -> new questionNotFoundException("해당 질문을 찾을 수 없습니다"));
    }

    private String createTime() {
        Date today = new Date();
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd ");
        SimpleDateFormat simpletime = new SimpleDateFormat("HH:mm");
        return simpleDate.format(today) + simpletime.format(today);
    }
}
