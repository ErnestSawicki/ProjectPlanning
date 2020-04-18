package pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.config.passwordConfig.ValidPassword;

import javax.validation.constraints.NotEmpty;

@Getter @Setter @ToString
public class UserDTOPasswordReset {
    @NotEmpty
    private String username;

    @NotEmpty
    @ValidPassword
    private String newPassword;

    @NotEmpty
    @ValidPassword
    private String confirmPassword;
}
