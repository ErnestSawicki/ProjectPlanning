package pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/modifyTask")
@Controller
public class ModifyTask {

    @GetMapping
    public String getTaskModifyPage(@RequestParam String taskId,
                                    Model model){
        System.out.println(taskId);
        model.addAttribute("taskId", taskId);
        return "/WEB-INF/views/modify-task.jsp";
    }
}
