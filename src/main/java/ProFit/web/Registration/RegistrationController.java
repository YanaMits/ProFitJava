package ProFit.web.Registration;

import ProFit.web.LoginUser.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegistrationController {
    @Autowired
    private RegistrationService registrationService;

    @GetMapping("/registration")
    public String showPage(Model model){
        model.addAttribute("user", new LoginUser());
        return "registration";
    }

//    @GetMapping("/login")
//    public String showLoginPage(Model model){
//        return "login";
//    }

    @PostMapping("/registration")
    public String saveUser(RegistrationRequest request, RedirectAttributes redirectAttributes) {
        registrationService.register(request);
        redirectAttributes.addFlashAttribute("message", "User Saved");
        return "redirect:/users";
    }
}
