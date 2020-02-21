package pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.mvc.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.model.*;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.repositories.ProjectRepository;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.repositories.TaskRepository;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.repositories.UserRepository;

import java.util.Arrays;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/projectTasks")
public class ProjectTasksController {

    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Autowired
    public ProjectTasksController(ProjectRepository projectRepository,
                                  TaskRepository taskRepository,
                                  UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
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
}
