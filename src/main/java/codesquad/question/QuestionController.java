package codesquad.question;

import codesquad.aspect.LogExecutionTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/questions")
public class QuestionController {
    private static final Logger log = LoggerFactory.getLogger(QuestionController.class);

    @Autowired
    private QuestionRepository questionRepository;

    @PostMapping("")
    @LogExecutionTime
    public String createQuestion(Result result, Question question) throws Throwable {
        question.setTime(createTime());
        log.error("========================테스트 중입니다========================");
        if (!result.isValid()) log.error(result.getErrorMessage());
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
