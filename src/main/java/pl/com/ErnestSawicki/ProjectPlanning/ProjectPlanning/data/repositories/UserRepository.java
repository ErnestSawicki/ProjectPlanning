package pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.model.Project;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.model.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findUserByUsername(String username);
    List<User> findAllByProjectsIs(Project project);
    User findUserByEmail(String email);
}
