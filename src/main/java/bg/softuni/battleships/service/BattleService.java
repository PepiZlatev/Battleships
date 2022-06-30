package bg.softuni.battleships.service;

import bg.softuni.battleships.models.dto.BattleDTO;
import bg.softuni.battleships.models.entity.Ship;
import bg.softuni.battleships.repository.ShipRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class BattleService {

    private final ShipRepository shipRepository;

    public BattleService(ShipRepository shipRepository) {
        this.shipRepository = shipRepository;
    }

    public void attack(BattleDTO battleDTO) {

        Optional<Ship> attacker = this.shipRepository.findById(battleDTO.getAttackedId());
        Optional<Ship> defender = this.shipRepository.findById(battleDTO.getDefenderId());

        if (attacker.isEmpty() || defender.isEmpty()) {
            throw new NoSuchElementException();
        }

        Ship attackerShip = attacker.get();
        Ship defenderShip = defender.get();

        long defenderHealth = defenderShip.getHealth() - attackerShip.getPower();

        if (defenderHealth <= 0) {
            this.shipRepository.delete(defenderShip);
        } else {
            defenderShip.setHealth(defenderHealth);
            this.shipRepository.save(defenderShip);
        }
    }
}
