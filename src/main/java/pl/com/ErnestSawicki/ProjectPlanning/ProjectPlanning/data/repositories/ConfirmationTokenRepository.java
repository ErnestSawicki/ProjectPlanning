package pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.model.ConfirmationToken;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {
    ConfirmationToken findByConfirmationToken(String confirmationToken);
}
