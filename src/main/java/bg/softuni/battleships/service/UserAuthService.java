package bg.softuni.battleships.service;

import bg.softuni.battleships.models.dto.LoginDTO;
import bg.softuni.battleships.models.dto.RegistrationDTO;
import bg.softuni.battleships.models.entity.User;
import bg.softuni.battleships.repository.UserRepository;
import bg.softuni.battleships.userSession.UserSession;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final UserSession userSession;

    public UserAuthService(UserRepository userRepository, PasswordEncoder encoder,
                           UserSession userSession) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.userSession = userSession;
    }

    public boolean register(RegistrationDTO registrationDTO) {

        Optional<User> byUsername = this.userRepository.findByUsername(registrationDTO.getUsername());

        if (byUsername.isPresent()) {
            return false;
        }

        Optional<User> byEmail = this.userRepository.findByEmail(registrationDTO.getEmail());

        if (byEmail.isPresent()) {
            return false;
        }

        if (!registrationDTO.getPassword().equals(registrationDTO.getConfirmPassword())) {
            return false;
        }

        User user = new User()
        .setUsername(registrationDTO.getUsername())
        .setFullName(registrationDTO.getFullName())
        .setEmail(registrationDTO.getEmail())
        .setPassword(encoder.encode(registrationDTO.getPassword()));

        this.userRepository.save(user);

        return true;
    }

    public boolean login(LoginDTO loginDTO) {
        Optional<User> user = this.userRepository.findByUsername(loginDTO.getUsername());
        boolean matchingPassword = this.encoder.matches(loginDTO.getPassword(), user.get().getPassword());


        if (matchingPassword && user.isPresent()) {
            this.userSession.login(user.get());
            return true;
        }
        return false;
    }

    public void logout() {
        this.userSession.logout();
    }

    public boolean isUserLoggedIn() {
        return this.userSession.getId() > 0;
    }

    public long getLoggedUserId() {
        return this.userSession.getId();
    }
}
