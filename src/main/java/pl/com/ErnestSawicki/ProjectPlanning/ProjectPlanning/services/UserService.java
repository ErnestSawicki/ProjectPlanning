package pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.services;

import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.model.User;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.dto.UserDTORegistration;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.dto.UserDTOUpdateProfile;

import java.security.Principal;

public interface UserService {

    boolean registerUser(UserDTORegistration userDTORegistration);
    boolean updateUserProfile(UserDTOUpdateProfile userDTOUpdateProfile);
    User getUserDetails(String username);
}
