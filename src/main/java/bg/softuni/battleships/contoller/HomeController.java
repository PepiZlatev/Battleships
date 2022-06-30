package bg.softuni.battleships.contoller;

import bg.softuni.battleships.models.dto.BattleDTO;
import bg.softuni.battleships.models.dto.ShipDTO;
import bg.softuni.battleships.service.ShipService;
import bg.softuni.battleships.service.UserAuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class HomeController {

    private final UserAuthService userAuthService;
    private final ShipService shipService;

    public HomeController(UserAuthService userAuthService, ShipService shipService) {
        this.userAuthService = userAuthService;
        this.shipService = shipService;
    }


    @ModelAttribute("battleDTO")
    public BattleDTO initBattleDTO() {
        return new BattleDTO();
    }

    @GetMapping("/")
    public String index() {

        if (this.userAuthService.isUserLoggedIn()) {
            return "redirect:/home";
        }
        return "index";
    }

    @GetMapping("/home")
    public String home(Model model) {

        if (!this.userAuthService.isUserLoggedIn()) {
            return "redirect:/";
        }

        long loggedUserId = this.userAuthService.getLoggedUserId();

        List<ShipDTO> ownShips = this.shipService.getShipsOwnedBy(loggedUserId);
        List<ShipDTO> enemyShips = this.shipService.getShipsNotOwnedBy(loggedUserId);
        List<ShipDTO> sortedShips = this.shipService.getAllSorted();

        model.addAttribute("ownShips", ownShips);
        model.addAttribute("enemyShips", enemyShips);
        model.addAttribute("sortedShips", sortedShips);

        return "home";
    }
}
