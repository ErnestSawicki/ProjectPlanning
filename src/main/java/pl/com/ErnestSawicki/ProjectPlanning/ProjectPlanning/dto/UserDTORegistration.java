package pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.config.passwordConfig.ValidPassword;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.model.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class UserDTORegistration {

    @NotEmpty
    private String username;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    @ValidPassword
    private String password;

    @Override
    public String toString() {
        return "UserDTORegistration{" +
                "userName='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
