package com.gbjavacourse.SpringBootTaskTracker.repositories.specifications;

import com.gbjavacourse.SpringBootTaskTracker.entities.Task;
import org.springframework.data.jpa.domain.Specification;

public class TaskSpecifications {
    public static Specification<Task> titleContains(String word) {
        return (Specification<Task>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), "%" + word + "%");
    }

    public static Specification<Task> statusEq(Long value) {
        return (Specification<Task>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("status"), value);
    }

    public static Specification<Task> ownerEq(Long value) {
        return (Specification<Task>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("owner"), value);
    }
}
