package pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.mvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.model.Project;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.repositories.ProjectRepository;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.repositories.UserRepository;

import java.security.Principal;
import java.util.List;

@Controller
public class ChooseProject {

    private ProjectRepository projectRepository;
    private UserRepository userRepository;

    @Autowired
    public ChooseProject(ProjectRepository projectRepository, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/chooseProject")
    public String getChooseProjectPage(Model model, Principal principal){
        List<Project> userProjects = projectRepository.findAllByParticipantsIs(userRepository.findUserByUsername(principal.getName()).get(0));
        model.addAttribute("userProjects", userProjects);
        return "choose-project";
    }
}
