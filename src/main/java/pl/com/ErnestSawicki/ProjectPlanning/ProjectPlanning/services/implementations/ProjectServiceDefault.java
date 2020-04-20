package pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.model.Project;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.model.enumeration.ProjectMaturity;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.repositories.ProjectRepository;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.dto.project.ProjectDTOCreate;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.services.ProjectService;

@Service
public class ProjectServiceDefault implements ProjectService {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectServiceDefault(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public void createProject(ProjectDTOCreate projectDTOCreate) {
        Project createdProject = new Project();
        createdProject.setPID(projectDTOCreate.getPID());
        createdProject.setProjectName(projectDTOCreate.getName());
        createdProject.setDescription(projectDTOCreate.getProjectDescription());
        createdProject.setProjectMaturity(ProjectMaturity.valueOf(projectDTOCreate.getProjectMaturity()));
        projectRepository.save(createdProject);
    }
}
