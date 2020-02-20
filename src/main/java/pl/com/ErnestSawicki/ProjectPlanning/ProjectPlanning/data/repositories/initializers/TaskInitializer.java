package pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.repositories.initializers;

import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.model.*;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.repositories.ProjectRepository;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.repositories.TaskRepository;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.repositories.UserRepository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Slf4j
public class TaskInitializer {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;

    @Autowired
    public TaskInitializer(TaskRepository taskRepository,
                           UserRepository userRepository,
                           ProjectRepository projectRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
    }

    public void initializeTaskSampleData() {
        log.info("TaskInitializer: Starting to initialize task sample data ....");
        Faker faker = new Faker();

        List<User> users = userRepository.findAll();
        List<Project> projects = projectRepository.findAll();

        for(int i =0; i < 100; i++){
            Task task = new Task();
            task.setTaskOwner(users.get(new Random().nextInt(9)));
            task.setTaskAssignee(users.get(new Random().nextInt(9)));
            task.setStartDate(LocalDate.now());
            task.setEndDate(LocalDate.of(2020,
                    new Random().nextInt(12) + 1,
                    new Random().nextInt(28) + 1));
            task.setTaskType(TaskType.values()[new Random().nextInt(8)]);
            task.setTaskStatus(TaskStatus.values()[new Random().nextInt(4)]);
            task.setPlannedHours(new Random().nextInt(10));
            task.setTaskDescription(faker.backToTheFuture().quote());
            task.setProject(projects.get(new Random().nextInt(3)));
            taskRepository.save(task);
        }

        log.info("TaskInitializer: ...finished with data initialization");
    }
}
