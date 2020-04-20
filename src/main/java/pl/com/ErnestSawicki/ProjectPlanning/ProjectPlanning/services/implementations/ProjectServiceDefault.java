package pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.services.implementations;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.model.Project;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.model.Task;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.model.enumeration.ProjectMaturity;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.repositories.ProjectRepository;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.repositories.TaskRepository;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.dto.project.ProjectDTOCreate;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.dto.project.ProjectDTODetails;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.services.ProjectService;

import java.util.List;

@Service
public class ProjectServiceDefault implements ProjectService {

    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;

    @Autowired
    public ProjectServiceDefault(ProjectRepository projectRepository, TaskRepository taskRepository) {
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
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

    @Override
    public ProjectDTODetails prepareProjectDetails(String projectId) {
        ProjectDTODetails projectDTODetails = new ProjectDTODetails();
        Project project = projectRepository.getOne(projectId);
        BeanUtils.copyProperties(project, projectDTODetails);
        List<Task> allTasksInProject = taskRepository.findAllByProject(project);
        Long hoursSpendOnProject = 0L;
        for (Task task : allTasksInProject) {
            hoursSpendOnProject += task.getPlannedHours();
        }
        projectDTODetails.setHoursSpend(hoursSpendOnProject);
        projectDTODetails.setAmountOfReworks(0L);

        return projectDTODetails;
    }
}
