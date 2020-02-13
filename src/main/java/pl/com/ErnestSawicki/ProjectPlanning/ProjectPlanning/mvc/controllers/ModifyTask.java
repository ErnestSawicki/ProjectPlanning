package pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.mvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.model.Task;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.repositories.TaskRepository;

@RequestMapping("/modifyTask")
@Controller
public class ModifyTask {

    private final TaskRepository taskRepository;

    @Autowired
    public ModifyTask(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping
    public String getTaskModifyPage(@RequestParam Long taskId,
                                    Model model){
        Task taskToModify = taskRepository.getOne(taskId);
        model.addAttribute("taskToModify", taskToModify);
        return "/WEB-INF/views/modify-task.jsp";
    }
}
