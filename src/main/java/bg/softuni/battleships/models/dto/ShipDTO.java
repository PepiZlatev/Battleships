package bg.softuni.battleships.models.dto;

import bg.softuni.battleships.models.entity.Ship;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class ShipDTO {

    private long id;
    @Size(min = 2, max = 10)
    @NotBlank
    private String name;

    @Positive
    private long power;

    @Positive
    private long health;

    @PastOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate created;

    @Positive
    private int category = -1;

    private String owner;

    public ShipDTO() {
    }

    public ShipDTO(Ship ship) {
        this.id = ship.getId();
        this.name = ship.getName();
        this.power = ship.getPower();
        this.health = ship.getHealth();
        this.created = ship.getCreated();
        this.owner = ship.getUser().getUsername();
    }

    public long getId() {
        return id;
    }

    public ShipDTO setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ShipDTO setName(String name) {
        this.name = name;
        return this;
    }

    public long getPower() {
        return power;
    }

    public ShipDTO setPower(long power) {
        this.power = power;
        return this;
    }

    public long getHealth() {
        return health;
    }

    public ShipDTO setHealth(long health) {
        this.health = health;
        return this;
    }

    public LocalDate getCreated() {
        return created;
    }

    public ShipDTO setCreated(LocalDate created) {
        this.created = created;
        return this;
    }

    public int getCategory() {
        return category;
    }

    public ShipDTO setCategory(int category) {
        this.category = category;
        return this;
    }

    public String getOwner() {
        return owner;
    }

    public ShipDTO setOwner(String owner) {
        this.owner = owner;
        return this;
    }
}
