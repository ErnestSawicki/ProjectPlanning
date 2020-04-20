package pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.mvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.model.Project;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.model.Task;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.model.User;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.model.enumeration.ProjectMaturity;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.model.enumeration.TaskStatus;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.repositories.ProjectRepository;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.repositories.TaskRepository;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.repositories.UserRepository;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.dto.project.ProjectDTOCreate;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.services.ProjectService;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

@RequestMapping("/project")
@Controller
public class ProjectController {

    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectRepository projectRepository, TaskRepository taskRepository, UserRepository userRepository, ProjectService projectService) {
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.projectService = projectService;
    }

    @GetMapping(value = "/createProject")
    public String getProjectPage(){
        return "project/project-create";

    }

    @PostMapping(value = "/createProject")
    public String createProject(ProjectDTOCreate projectDTOCreate){
        projectService.createProject(projectDTOCreate);
        return "redirect:/";
    }

    @GetMapping(value = "/projectTasks")
    public String getProjectTasks(@RequestParam String choseProject,
                                  Model model){
        Project project = projectRepository.getOne(choseProject);
        List<Task> allProjectTasks = taskRepository.findAllByProject(project);
        model.addAttribute("projectTasks", allProjectTasks);

        List<TaskStatus> taskStatuses = Arrays.asList(TaskStatus.values());
        model.addAttribute("taskStatuses", taskStatuses);

        List<User> projectParticipants = userRepository.findAllByProjectsIs(project);
        model.addAttribute("projectParticipants", projectParticipants);
        return "project/project-tasks";
    }

    @GetMapping("/chooseProject")
    public String getChooseProjectPage(Model model, Principal principal){
        List<Project> userProjects = projectRepository.findAllByParticipantsIs(userRepository.findUserByUsername(principal.getName()).get(0));
        model.addAttribute("userProjects", userProjects);
        return "project/project-chooseProject";
    }
}

