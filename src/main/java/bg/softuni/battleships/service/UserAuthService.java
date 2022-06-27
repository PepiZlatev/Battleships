package bg.softuni.battleships.service;

import bg.softuni.battleships.models.dto.RegistrationDTO;
import bg.softuni.battleships.models.entity.User;
import bg.softuni.battleships.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public UserAuthService(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
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
}
