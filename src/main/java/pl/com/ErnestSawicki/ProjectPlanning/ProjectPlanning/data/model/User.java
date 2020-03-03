package pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;

import java.util.Set;

@Entity
@Table(name = "users")
@Getter @Setter
public class User extends EntityBase {

    @Column(name = "username", unique = true, nullable = false)
    private String username;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "ROLE")
    private String role;
    @Column(name = "active")
    private Boolean active;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Employee_Project",
    joinColumns = {@JoinColumn(name = "employee_id")},
    inverseJoinColumns = {@JoinColumn(name = "project_id")})
    Set<Project> projects = new HashSet<>();

    @OneToMany
    private List<Task> ownedTasks;

    @OneToMany
    private List<Task> assignedTasks;


    @Override
    public String toString() {
        return "User{" +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
