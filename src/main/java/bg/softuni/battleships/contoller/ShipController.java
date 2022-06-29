package bg.softuni.battleships.contoller;

import bg.softuni.battleships.models.dto.ShipDTO;
import bg.softuni.battleships.service.ShipService;
import bg.softuni.battleships.service.UserAuthService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class ShipController {

    private final ShipService shipService;
    private final UserAuthService userService;

    public ShipController(ShipService shipService, UserAuthService userService) {
        this.shipService = shipService;
        this.userService = userService;
    }

    @ModelAttribute("shipDTO")
    public ShipDTO initShipDTO() {
        return new ShipDTO();
    }

    @GetMapping("/ship-add")
    public String addShip() {

        if (!this.userService.isUserLoggedIn()) {
            return "redirect:/";
        }

        return "ship-add";
    }


    @PostMapping("/ship-add")
    public String addShip(@Valid ShipDTO shipDTO, BindingResult bindingResult,
                          RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("shipDTO", shipDTO);
            redirectAttributes
                    .addFlashAttribute(
                            "org.springframework.validation.BindingResult.shipDTO",
                            bindingResult);

            return "redirect:/ship-add";
        }

        if (!this.shipService.addShip(shipDTO)) {
            redirectAttributes.addFlashAttribute("shipDTO", shipDTO);
            redirectAttributes.addFlashAttribute("existingShip", true);

            return "redirect:/ship-add";
        }

        return "redirect:/home";

    }
}
