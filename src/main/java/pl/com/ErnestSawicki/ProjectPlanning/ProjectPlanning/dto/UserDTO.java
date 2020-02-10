package pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.dto;

import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.config.passwordConfig.ValidPassword;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ValidPassword
public class UserDTO {

    @NotNull
    @Size(min =1, message = "{Size.userDto.firstName}")
    private String firstName;

    @NotNull
    @Size(min =1, message = "{Size.userDto.firstName}")
    private String lastName;

    @ValidPassword
    private String password;

    @NotNull
    @Size(min = 1)
    private String matchingPassword;

}
