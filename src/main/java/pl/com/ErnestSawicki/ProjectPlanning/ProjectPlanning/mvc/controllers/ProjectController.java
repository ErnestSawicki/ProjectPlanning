package pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.mvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.model.Project;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.model.ProjectMaturity;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.repositories.ProjectRepository;

@RequestMapping("/createProject")
@Controller
public class ProjectController {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectController(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @GetMapping
    public String getProjectPage(){
        return "/WEB-INF/views/create-project.jsp";

    }

    @PostMapping
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
}

