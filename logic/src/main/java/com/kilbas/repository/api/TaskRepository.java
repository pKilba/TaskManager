package com.kilbas.repository.api;

import com.kilbas.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends BaseRepository<Task>{

    /**
     *
     * @param name name task
     * @return task
     */
    Optional<Task> findByName(String name);



    //todo
    /**
     *
     * @param author author task
     * @param executor task
     * @param page page for pagination
     * @param size size for pagination
     * @return list tasks
     */
    List<Task> findAllWithSortingFiltering(
            String author, String executor,
            int page, int size);
}
