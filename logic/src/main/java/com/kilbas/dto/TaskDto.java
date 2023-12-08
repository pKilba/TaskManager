package com.kilbas.dto;

import com.kilbas.model.PriorityTask;
import com.kilbas.model.StatusTask;
import com.kilbas.model.Task;

public class TaskDto {
    private String name;
    private String description;
    private StatusTask statusTask;
    private PriorityTask priorityTask;
    private String author;
    private String executor;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public StatusTask getStatusTask() {
        return statusTask;
    }

    public void setStatusTask(StatusTask statusTask) {
        this.statusTask = statusTask;
    }

    public PriorityTask getPriorityTask() {
        return priorityTask;
    }

    public void setPriorityTask(PriorityTask priorityTask) {
        this.priorityTask = priorityTask;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getExecutor() {
        return executor;
    }

    public void setExecutor(String executor) {
        this.executor = executor;
    }

    public static TaskDto fromTask(Task task) {
        TaskDto taskDto = new TaskDto();
        taskDto.setName(task.getName());
        taskDto.setDescription(task.getDescription());
        taskDto.setStatusTask(task.getStatusTask());
        taskDto.setPriorityTask(task.getPriorityTask());
        taskDto.setAuthor(task.getAuthor());
        taskDto.setExecutor(task.getExecutor());
        return taskDto;
    }

    public Task toTask() {
        Task task = new Task();
        task.setName(this.getName());
        task.setDescription(this.getDescription());
        task.setStatusTask(this.getStatusTask());
        task.setPriorityTask(this.getPriorityTask());
        task.setAuthor(this.getAuthor());
        task.setExecutor(this.getExecutor());
        return task;
    }
}
