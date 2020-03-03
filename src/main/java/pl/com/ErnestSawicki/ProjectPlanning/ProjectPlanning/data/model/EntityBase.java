package pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@MappedSuperclass
@Getter @Setter
public abstract class EntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "creation_date_time")
    private LocalDateTime creationDataTime;

    @Column(name = "last_modification_data_time")
    private LocalDateTime lastModificationDateTime;

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return this.id.equals(((EntityBase) o).id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @PrePersist
    public void prePersist(){
        this.creationDataTime = LocalDateTime.now();
        this.lastModificationDateTime = null;
    }

    @PreUpdate
    public void preUpdate(){
        this.lastModificationDateTime = LocalDateTime.now();
    }
}
