package bg.softuni.battleships.contoller;

import bg.softuni.battleships.models.dto.BattleDTO;
import bg.softuni.battleships.service.BattleService;
import bg.softuni.battleships.service.UserAuthService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class BattleController {

    private final UserAuthService userService;
    private final BattleService battleService;

    public BattleController(UserAuthService userService, BattleService battleService) {
        this.userService = userService;
        this.battleService = battleService;
    }

    @PostMapping("/battle")
    public String battle(@Valid BattleDTO battleDTO, BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {

        if (!this.userService.isUserLoggedIn()) {
            return "redirect:/";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("battleDTO", battleDTO);
            redirectAttributes
                    .addFlashAttribute(
                            "org.springframework.validation.BindingResult.battleDTO",
                            bindingResult);

            return "redirect:/home";
        }

        this.battleService.attack(battleDTO);

        return "redirect:/home";
    }
}
