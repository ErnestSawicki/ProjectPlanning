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
        if (isNotNull(criteria.getTaskStatus()))
            predicates.add(criteriaBuilder.equal(root.get("taskStatus"), criteria.getTaskStatus()));
        if (isNotNull(criteria.getTaskOwner()))
            predicates.add(criteriaBuilder.equal(root.get("taskOwner"), criteria.getTaskOwner()));
        if (isNotNull(criteria.getTaskType()))
            predicates.add(criteriaBuilder.equal(root.get("taskType"), criteria.getTaskType()));

        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    }

    private boolean isNotNull(Object object) {
        return object != null;
    }
}
