package bg.softuni.battleships.models.entity;

import bg.softuni.battleships.models.enums.ShipType;

import javax.persistence.*;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity{

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false, unique = true)
    private ShipType name;

    private String description;

    public Category() {
    }

    public Category(ShipType name) {
        this.name = name;
    }

    public ShipType getName() {
        return name;
    }

    public Category setName(ShipType name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Category setDescription(String description) {
        this.description = description;
        return this;
    }
}
