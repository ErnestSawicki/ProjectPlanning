package pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.repositories.specification;

import org.springframework.data.jpa.domain.Specification;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.model.Task;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class TaskSpecification implements Specification<Task> {

    private final Task criteria;

    public TaskSpecification(Task criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<Task> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(criteriaBuilder.equal(root.get("taskStatus"), criteria.getTaskStatus()));
        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}
