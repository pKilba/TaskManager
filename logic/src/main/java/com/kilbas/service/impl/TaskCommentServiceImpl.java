package com.kilbas.service.impl;

import com.kilbas.dto.TaskCommentDto;
import com.kilbas.exception.NotFoundEntityException;
import com.kilbas.model.Task;
import com.kilbas.model.TaskComment;
import com.kilbas.model.User;
import com.kilbas.repository.api.TaskCommentRepository;
import com.kilbas.service.api.TaskCommentService;
import com.kilbas.service.api.TaskService;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class TaskCommentServiceImpl implements TaskCommentService {

    private static final String TASK_NOT_EXIST = "Task not exist";
    private final TaskCommentRepository taskCommentRepository;
    private final TaskService taskService;
    private final UserServiceImpl userService;


    public TaskCommentServiceImpl(TaskCommentRepository taskCommentRepository, TaskService taskService, UserServiceImpl userService) {
        this.taskCommentRepository = taskCommentRepository;
        this.taskService = taskService;
        this.userService = userService;
    }

    @Transactional
    @Override
    public void createTaskComment(int id, TaskCommentDto taskCommentDto) {

        Task task = taskService.findById(id);
        if (task == null) {
            throw new NotFoundEntityException(TASK_NOT_EXIST);
        }
        User user = userService.findByUserEmail(taskCommentDto.getEmail());
        TaskComment taskComment = new TaskComment(task, user, taskCommentDto.getContent());
        taskCommentRepository.create(taskComment);

    }
}
