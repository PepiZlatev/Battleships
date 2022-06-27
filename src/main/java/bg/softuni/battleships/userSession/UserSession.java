package bg.softuni.battleships.userSession;

import bg.softuni.battleships.models.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class UserSession {

    private long id;

    private String username;

    public long getId() {
        return id;
    }

    public UserSession setId(long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserSession setUsername(String username) {
        this.username = username;
        return this;
    }

    public void logout() {
        this.id = 0;
        this.username = null;
    }

    public void login(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
    }
}
