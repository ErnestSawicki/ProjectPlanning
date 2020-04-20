package pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.dto.project;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.model.User;

import java.util.List;

@Getter @Setter @ToString
public class ProjectDTODetails {

    private String PID;
    private String projectName;
    private String description;
    private List<User> participants;
    private Long hoursSpend;
    private Long amountOfReworks;
}
