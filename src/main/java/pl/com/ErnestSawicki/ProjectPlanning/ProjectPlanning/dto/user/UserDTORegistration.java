package pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.dto.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.config.passwordConfig.ValidPassword;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter @Setter @ToString
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

}
