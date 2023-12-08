package com.kilbas.repository.impl;

import com.kilbas.model.Task;
import com.kilbas.repository.api.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TaskRepositoryImpl extends AbstractRepository<Task> implements TaskRepository {


    @Autowired
    public TaskRepositoryImpl(EntityManager entityManager) {
        super(entityManager, Task.class);
    }

    public Optional<Task> findByName(String name) {

        return findByField("name", name);
    }

    public List<Task> findAllWithSortingFiltering(String author,
                                                  String executor,
                                                  int page, int size) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Task> query = criteriaBuilder.createQuery(Task.class);
        Root<Task> root = query.from(Task.class);
        query.select(root);

        List<Predicate> predicates = new ArrayList<>();

        if (author != null) {
            predicates.add(criteriaBuilder.equal(root.get("author"), author));
        }
        if (executor != null) {
            predicates.add(criteriaBuilder.equal(root.get("executor"), executor));
        }

        if (!predicates.isEmpty()) {
            query.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
        }

        query.orderBy(criteriaBuilder.asc(root.get("name")));

        return entityManager.createQuery(query)
                .setFirstResult(page * size)
                .setMaxResults(size)
                .getResultList();
    }

}
