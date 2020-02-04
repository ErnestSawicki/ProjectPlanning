package pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.mvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.model.Task;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.repositories.TaskRepository;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/userTasks")
public class UserTasksController {

    private final TaskRepository taskRepository;

    @Autowired
    public UserTasksController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping
    public String getUserTasksPage(Principal principal, Model model){
        String username = principal.getName();
        List<Task> userTasks = taskRepository.findAllByTaskOwnerUsernameOrderByStartDateDesc(username);
        model.addAttribute("userTasks", userTasks);
        return "/WEB-INF/views/user-tasks.jsp";
    }
}
