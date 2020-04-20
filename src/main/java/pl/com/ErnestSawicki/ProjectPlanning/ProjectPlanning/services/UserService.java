package pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.services;

import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.model.User;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.dto.user.UserDTORegistration;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.dto.user.UserDTOUpdateProfile;

public interface UserService {

    boolean registerUser(UserDTORegistration userDTORegistration);
    boolean updateUserProfile(UserDTOUpdateProfile userDTOUpdateProfile);
    User getUserDetails(String username);
}
