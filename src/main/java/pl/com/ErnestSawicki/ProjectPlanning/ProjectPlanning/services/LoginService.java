package pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.services;

import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.dto.UserDTOPasswordReset;

public interface LoginService {

    void sendForgotPasswordEmail(String userEmail);
    void resetUserPassword(UserDTOPasswordReset userDTOPasswordReset);
}
