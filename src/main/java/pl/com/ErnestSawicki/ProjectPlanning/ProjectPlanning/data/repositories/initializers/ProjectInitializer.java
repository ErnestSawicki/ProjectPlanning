package pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.repositories.initializers;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.model.Project;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.model.enumeration.ProjectMaturity;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.repositories.ProjectRepository;

import java.util.Locale;
import java.util.Random;

@Slf4j
public class ProjectInitializer {

    private final ProjectRepository projectRepository;


    @Autowired
    public ProjectInitializer(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }


    public void initializeProjectSampleData() {
        log.info("ProjectInitializer: Starting to initialize project sample data ....");
        Faker faker = new Faker();
        FakeValuesService fakeValuesService = new FakeValuesService(new Locale("en-GB"), new RandomService());

        for(int i = 0; i< 10; i++){

            Project project = new Project();

            project.setProjectMaturity(ProjectMaturity.values()[new Random().nextInt(4)]);
            project.setDescription(faker.harryPotter().quote());
            project.setProjectName(faker.book().title());
            project.setPID(fakeValuesService.numerify("#####"));
            projectRepository.save(project);

        }
        log.info("ProjectInitializer: ...finished with data initialization");
    }
}
