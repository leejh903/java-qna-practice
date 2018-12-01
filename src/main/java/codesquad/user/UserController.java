package codesquad.user;

import codesquad.aspect.CustomAnnotation;
import codesquad.question.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("")
    public String listUser(@CustomAnnotation Question question, Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "user/list";
    }

    @PostMapping("")
    public String createUser(User user) {
        userRepository.save(user);
        return "redirect:/users";
    }

    @GetMapping("/profile/{id}")
    public String showProfile(@PathVariable Long id, Model model) throws IdNotFoundException{
        User user = searchUser(id);
        model.addAttribute("user", user);
        return "user/profile";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) throws IdNotFoundException{
        User user = searchUser(id);
        model.addAttribute("user", user);
        return "user/updateForm";
    }

    @PutMapping("/update/{id}")
    public String updateUser(@PathVariable Long id, User userUpdated) {
        User user = searchUser(id);
        user.update(userUpdated);
        userRepository.save(user);
        return "redirect:/users";
    }

    private User searchUser(@PathVariable Long id) {
        return userRepository.findById(id).orElseThrow(() -> new IdNotFoundException("아이디를 찾을 수 없습니다"));
    }

}
