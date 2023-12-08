package com.kilbas.repository.impl;

import com.kilbas.model.TaskComment;
import com.kilbas.repository.api.TaskCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class TaskCommentImpl extends AbstractRepository<TaskComment> implements TaskCommentRepository  {
    @Autowired
    public TaskCommentImpl(EntityManager entityManager) {
        super(entityManager, TaskComment.class);
    }

}
