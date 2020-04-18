package pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "confirmation_tokens")
@Getter @Setter
public class ConfirmationToken extends EntityBase {

    @Column(name = "confirmation_token")
    private String confirmationToken;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    @Column
    private boolean tokenUsed;

    public ConfirmationToken() {
    }

    public ConfirmationToken(User user){
        this.user = user;
        confirmationToken = UUID.randomUUID().toString();
        tokenUsed = false;
    }
}
