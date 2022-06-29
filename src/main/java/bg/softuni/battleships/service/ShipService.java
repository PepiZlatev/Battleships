package bg.softuni.battleships.service;

import bg.softuni.battleships.models.dto.ShipDTO;
import bg.softuni.battleships.models.entity.Category;
import bg.softuni.battleships.models.entity.Ship;
import bg.softuni.battleships.models.entity.User;
import bg.softuni.battleships.models.enums.ShipType;
import bg.softuni.battleships.repository.CategoryRepository;
import bg.softuni.battleships.repository.ShipRepository;
import bg.softuni.battleships.repository.UserRepository;
import bg.softuni.battleships.userSession.UserSession;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class ShipService {

    private final ShipRepository shipRepository;
    private final CategoryRepository categoryRepository;
    private final UserSession userSession;

    private final UserRepository userRepository;


    public ShipService(ShipRepository shipRepository, CategoryRepository categoryRepository,
                       UserSession userSession, UserRepository userRepository) {
        this.shipRepository = shipRepository;
        this.categoryRepository = categoryRepository;
        this.userSession = userSession;
        this.userRepository = userRepository;
    }

    public boolean addShip(ShipDTO shipDTO) {

        Optional<Ship> byName = this.shipRepository.findByName(shipDTO.getName());

        if (byName.isPresent()) {
            return false;
        }

        ShipType shipType = null;

        switch (shipDTO.getCategory()) {
            case 1 -> shipType = ShipType.BATTLE;
            case 2 -> shipType = ShipType.CARGO;
            case 3 -> shipType = ShipType.PATROL;
            default -> {}
        };

        Category category = this.categoryRepository.findByName(shipType);

        Optional<User> user = this.userRepository.findByUsername(userSession.getUsername());

        Ship ship = new Ship()
                .setName(shipDTO.getName())
                .setPower(shipDTO.getPower())
                .setHealth(shipDTO.getHealth())
                .setCreated(shipDTO.getCreated())
                .setCategory(category)
                .setUser(user.get());


        this.shipRepository.save(ship);

        System.out.println(ship);

        return true;

    }
}
