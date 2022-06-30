package bg.softuni.battleships.repository;

import bg.softuni.battleships.models.dto.ShipDTO;
import bg.softuni.battleships.models.entity.Ship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShipRepository extends JpaRepository<Ship, Long> {
    Optional<Ship> findByName(String name);

    List<Ship> findByUserIdNot(long loggedUserId);

    List<Ship> findByOrderByHealthAscNameDescPowerAsc();

    List<Ship> findByUserId(long loggedUserId);
}
