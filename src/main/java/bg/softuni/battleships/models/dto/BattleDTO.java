package bg.softuni.battleships.models.dto;

import javax.validation.constraints.Positive;

public class BattleDTO {

    @Positive
    private long attackedId;

    @Positive
    private long defenderId;

    public long getAttackedId() {
        return attackedId;
    }

    public BattleDTO setAttackedId(long attackedId) {
        this.attackedId = attackedId;
        return this;
    }

    public long getDefenderId() {
        return defenderId;
    }

    public BattleDTO setDefenderId(long defenderId) {
        this.defenderId = defenderId;
        return this;
    }
}
