package com.gbjavacourse.SpringBootTaskTracker.repositories.specifications;

import com.gbjavacourse.SpringBootTaskTracker.entities.Task;
import org.springframework.data.jpa.domain.Specification;

public class TaskSpecifications {
    public static Specification<Task> titleContains(String word) {
        return (Specification<Task>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), "%" + word + "%");
    }

    public static Specification<Task> status_idEq(Long value) {
        return (Specification<Task>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("status_id"), value);
    }

    public static Specification<Task> owner_idEq(Long value) {
        return (Specification<Task>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("owner_id"), value);
    }
}
