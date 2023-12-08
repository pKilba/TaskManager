package com.kilbas.service.api;

import com.kilbas.model.Task;

import java.util.List;

public interface TaskService {


    /**
     * @param email email user
     * @param id    id  task
     */
    void checkAccessoryTask(String email, int id);

    /**
     * @param entity task
     * @return id task
     */
    Task create(Task entity);

    /**
     * @return list of tasks
     */
    List<Task> findAllWithFiltering(String author, String executor, int page, int size);

    /**
     * @param id id task
     * @return task
     */
    Task findById(int id);


    /**
     * @param id id task
     */
    void deleteById(int id);

    /**
     * @param id      id task
     * @param taskDto task and set tag
     * @return task
     */
    Task updateById(int id, Task taskDto, String email);


}
