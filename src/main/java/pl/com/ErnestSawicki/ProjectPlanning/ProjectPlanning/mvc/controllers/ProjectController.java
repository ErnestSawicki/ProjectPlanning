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

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

@RequestMapping("/project")
@Controller
public class ProjectController {

    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Autowired
    public ProjectController(ProjectRepository projectRepository, TaskRepository taskRepository, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    @GetMapping(value = "/createProject")
    public String getProjectPage(){
        return "project-create";

    }

    @PostMapping(value = "/createProject")
    public String createProject(@RequestParam String PID,
                                @RequestParam String name,
                                @RequestParam String projectDescription,
                                @RequestParam String projectMaturity){
        Project createdProject = new Project();
        createdProject.setPID(PID);
        createdProject.setProjectName(name);
        createdProject.setDescription(projectDescription);
        createdProject.setProjectMaturity(ProjectMaturity.valueOf(projectMaturity));
        projectRepository.save(createdProject);
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
        return "project-tasks";
    }

    @GetMapping("/chooseProject")
    public String getChooseProjectPage(Model model, Principal principal){
        List<Project> userProjects = projectRepository.findAllByParticipantsIs(userRepository.findUserByUsername(principal.getName()).get(0));
        model.addAttribute("userProjects", userProjects);
        return "project-chooseProject";
    }
}

