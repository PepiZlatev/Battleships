package bg.softuni.battleships.models.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class LoginDTO {

    @Size(min = 3, max = 10)
    @NotBlank
    public String username;


    @Size(min = 3)
    @NotBlank
    public String password;

    public String getUsername() {
        return username;
    }

    public LoginDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public LoginDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public LoginDTO() {


    }
}
