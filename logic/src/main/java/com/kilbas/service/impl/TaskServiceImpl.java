package com.kilbas.service.impl;

import com.kilbas.exception.AccessEntityException;
import com.kilbas.exception.NotFoundEntityException;
import com.kilbas.model.Task;
import com.kilbas.repository.api.TaskRepository;
import com.kilbas.service.api.TaskService;
import com.kilbas.validator.impl.TaskValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class TaskServiceImpl implements TaskService {

    private static final String TASK_NOT_EXIST = "Task not exist";
    private static final String TASK_NOT_FOUND = "Task not found";
    private static final String DONT_HAVE_ACCESS = "You don't have access on this task";
    private final TaskRepository taskRepository;

    private final TaskValidator taskValidator;


    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository, TaskValidator taskValidator
    ) {
        this.taskRepository = taskRepository;
        this.taskValidator = taskValidator;
    }

    @Override
    @Transactional
    public Task create(Task task) {
        taskValidator.isValid(task);
        return taskRepository.create(task);
    }


    @Override
    public List<Task> findAllWithFiltering(
            String author, String executor,
            int page, int size) {
        return taskRepository.findAllWithSortingFiltering(author, executor, page, size);
    }

    @Override
    public Task findById(int id) {
        Optional<Task> taskOptional = taskRepository.findById(id);
        if (!taskOptional.isPresent()) {
            throw new NotFoundEntityException(TASK_NOT_FOUND);
        }
        return taskOptional.get();
    }


    public void checkAccessoryTask(String email, int id) {
        Optional<Task> taskOptional = taskRepository.findById(id);
        if (!taskOptional.isPresent()) {
            throw new NotFoundEntityException(TASK_NOT_FOUND);
        }
        if (!Objects.equals(taskOptional.get().getAuthor(), email)) {
            throw new AccessEntityException("Access exception");
        }
    }

    @Override
    public void deleteById(int id) {
        taskRepository.deleteById(id);
    }

    @Transactional
    public Task updateById(int id, Task task, String email) {

        Task presentTask = taskRepository.findById(id).orElseThrow(
                () -> new NotFoundEntityException(TASK_NOT_EXIST));

        //todo вынести в отдельную логику ??
        if (Objects.equals(email, presentTask.getAuthor()) || Objects.equals(email, presentTask.getAuthor()) && Objects.equals(email, presentTask.getExecutor())) {
            if (!Objects.equals(task.getName(), presentTask.getName()) && task.getName() != null) {
                presentTask.setName(task.getName());
            }
            if (!Objects.equals(task.getAuthor(), presentTask.getAuthor()) && task.getAuthor() != null) {
                presentTask.setAuthor(task.getAuthor());
            }
            if (!Objects.equals(task.getDescription(), presentTask.getDescription()) && task.getDescription() != null) {
                presentTask.setDescription(task.getDescription());
            }
            if
            (task.getStatusTask() != presentTask.getStatusTask() && task.getStatusTask() != null) {
                presentTask.setStatusTask(task.getStatusTask());
            }
            if (task.getPriorityTask() != presentTask.getPriorityTask() && task.getPriorityTask() != null) {
                presentTask.setPriorityTask(task.getPriorityTask());
            }
        }
        //todo
        else if (Objects.equals(email, presentTask.getExecutor()) && task.getStatusTask() != null) {

            if
            (task.getStatusTask() != presentTask.getStatusTask() && task.getStatusTask() != null) {
                presentTask.setStatusTask(task.getStatusTask());
            }
        } else {
            throw new AccessEntityException(DONT_HAVE_ACCESS);

        }
        return taskRepository.update(presentTask);
    }

}
