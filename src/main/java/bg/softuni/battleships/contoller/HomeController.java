package bg.softuni.battleships.contoller;

import bg.softuni.battleships.service.UserAuthService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final UserAuthService userAuthService;

    public HomeController(UserAuthService userAuthService) {
        this.userAuthService = userAuthService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/home")
    public String home() {

        if (!this.userAuthService.isUserLoggedIn()) {
            return "redirect:/";
        }

        return "home";
    }
}
