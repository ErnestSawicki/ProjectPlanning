package pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.services.implementations;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.model.User;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.repositories.UserRepository;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.dto.user.UserDTORegistration;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.dto.user.UserDTOUpdateProfile;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.services.UserService;

@Service
@Slf4j
public class UserServiceDefault implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceDefault(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean registerUser(UserDTORegistration userDTORegistration) {
        log.debug("UserServiceDefault-registerUser: userDTORegistration={}", userDTORegistration.toString());
        log.debug("UserServiceDefault-registerUser: User registration started...");
        User userToRegister = new User();
        BeanUtils.copyProperties(userDTORegistration, userToRegister);
        userToRegister.setPassword(passwordEncoder.encode(userToRegister.getPassword()));
        userToRegister.setRole("USER");
        userToRegister.setActive(true);
        log.debug("UserServiceDefault-registerUser: user to save={}", userToRegister.toString());
        log.debug("UserServiceDefault-registerUser: ... user registration finished");
        userRepository.save(userToRegister);
        return true;
    }

    @Override
    public boolean updateUserProfile(UserDTOUpdateProfile userDTOUpdateProfile) {
        log.debug("UserServiceDefault-updateUserProfile: userDTORegistration={}", userDTOUpdateProfile.toString());
        log.debug("UserServiceDefault-updateUserProfile: User update started...");
        User userToUpdate = userRepository.findUserByUsername(userDTOUpdateProfile.getUsername()).get(0);
        BeanUtils.copyProperties(userDTOUpdateProfile, userToUpdate);
        log.debug("UserServiceDefault-updateUserProfile: updatedUser to save={}", userToUpdate.toString());
        log.debug("UserServiceDefault-updateUserProfile: ... user update finished");
        userRepository.save(userToUpdate);
        return true;
    }

    @Override
    public User getUserDetails(String username) {
        return userRepository.findUserByUsername(username).get(0);
    }
}
