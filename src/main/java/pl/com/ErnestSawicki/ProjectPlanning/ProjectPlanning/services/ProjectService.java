package pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.services;

import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.dto.project.ProjectDTOCreate;

public interface ProjectService {

    void createProject(ProjectDTOCreate projectDTOCreate);
}
