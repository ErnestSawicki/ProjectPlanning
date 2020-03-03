package pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.services;

import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.model.User;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.dto.UserDTORegistration;

public interface UserService {

    boolean registerUser(UserDTORegistration userDTORegistration);
    User getUserDetails(String username);
}
