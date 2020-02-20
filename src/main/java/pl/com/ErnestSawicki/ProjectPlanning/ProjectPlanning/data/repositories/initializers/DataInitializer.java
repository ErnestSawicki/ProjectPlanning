/*
package pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.repositories.initializers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.model.Project;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.model.User;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.repositories.PartRepository;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.repositories.ProjectRepository;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.repositories.TaskRepository;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.repositories.UserRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Random;

@Component
@Slf4j
@Transactional
public class DataInitializer implements CommandLineRunner {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final PasswordEncoder passwordEncoder;
    private final PartRepository partRepository;

    @Autowired
    public DataInitializer(ProjectRepository projectRepository, UserRepository userRepository, TaskRepository taskRepository, PasswordEncoder passwordEncoder, PartRepository partRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
        this.passwordEncoder = passwordEncoder;
        this.partRepository = partRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        ProjectInitializer projectInitializer = new ProjectInitializer(projectRepository);
        projectInitializer.initializeProjectSampleData();

        UserInitializer userInitializer = new UserInitializer(userRepository, passwordEncoder,projectRepository);
        userInitializer.initializeUserSampleData();

        TaskInitializer taskInitializer = new TaskInitializer(taskRepository, userRepository, projectRepository);
        taskInitializer.initializeTaskSampleData();

        List<User> users = userRepository.findAll();
        List<Project> projects = projectRepository.findAll();

        for (int i =0; i < users.size(); i++){
            User user = userRepository.findAll().get(i);
            Project project = projects.get(new Random().nextInt(9));
            user.getProjects().add(project);
            userRepository.save(user);
            project.getParticipants().add(user);
            projectRepository.save(project);
        }

        PartInitializer partInitializer = new PartInitializer();
        partInitializer.initializePartSampleData(partRepository);
    }
}
*/
