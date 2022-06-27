package bg.softuni.battleships.contoller;

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
}
