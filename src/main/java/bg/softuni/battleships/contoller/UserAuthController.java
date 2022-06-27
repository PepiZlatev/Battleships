package bg.softuni.battleships.contoller;

import bg.softuni.battleships.models.dto.LoginDTO;
import bg.softuni.battleships.models.dto.RegistrationDTO;
import bg.softuni.battleships.service.UserAuthService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class UserAuthController {

    private final UserAuthService userAuthService;

    public UserAuthController(UserAuthService userAuthService) {
        this.userAuthService = userAuthService;
    }


    @ModelAttribute("registrationDTO")
    private RegistrationDTO initRegistrationDTO() {
        return new RegistrationDTO();
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid RegistrationDTO registrationDTO,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || !this.userAuthService.register(registrationDTO)){
            redirectAttributes.addFlashAttribute("registrationDTO", registrationDTO);
            redirectAttributes
                    .addFlashAttribute(
                            "org.springframework.validation.BindingResult.registrationDTO",
                            bindingResult);

            return "redirect:/register";

        }

        return "redirect:/home";

    }

    @ModelAttribute("loginDTO")
    public LoginDTO initLoginDTO() {
        return new LoginDTO();
    }


    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@Valid LoginDTO loginDTO, BindingResult bindingResult,
                        RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("loginDTO", loginDTO);
            redirectAttributes
                    .addFlashAttribute(
                            "org.springframework.validation.BindingResult.loginDTO",
                    bindingResult);

            return "redirect:/login";

        }

        if (!this.userAuthService.login(loginDTO)) {
            redirectAttributes.addFlashAttribute("loginDTO", loginDTO);
            redirectAttributes.addFlashAttribute("badCredentials", true);

            return "redirect:/login";
        }

        return "redirect:/home";
    }


    @GetMapping("/logout")
    public String logout() {
        if (this.userAuthService.isUserLoggedIn()) {

            this.userAuthService.logout();

            return "redirect:/";
        }

        return "redirect:/home";
    }
}
