package pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.services;

import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.dto.project.ProjectDTOCreate;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.dto.project.ProjectDTODetails;

public interface ProjectService {

    void createProject(ProjectDTOCreate projectDTOCreate);

    ProjectDTODetails prepareProjectDetails(String projectId);
}
