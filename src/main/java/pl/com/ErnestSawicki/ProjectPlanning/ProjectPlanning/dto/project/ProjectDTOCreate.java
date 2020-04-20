package pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.dto.project;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProjectDTOCreate {

    private String PID;
    private String name;
    private String projectDescription;
    private String projectMaturity;
}
