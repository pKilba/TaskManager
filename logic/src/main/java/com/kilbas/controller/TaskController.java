package com.kilbas.controller;


import com.kilbas.dto.TaskDto;
import com.kilbas.model.Task;
import com.kilbas.service.api.TaskService;
import com.kilbas.validator.impl.RequestParametersValidator;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

import static com.kilbas.util.RequestParammetr.*;


@Tag(name = "Задачи", description = "контроллер для задач")
@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;
    private final RequestParametersValidator requestParametersValidator;

    @Autowired
    public TaskController(TaskService taskService,
                          RequestParametersValidator requestParametersValidator) {
        this.taskService = taskService;
        this.requestParametersValidator = requestParametersValidator;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @SecurityRequirement(name = "JWT")
    public Task create(@RequestBody TaskDto taskDto) {
        return taskService.create(taskDto.toTask());

    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @SecurityRequirement(name = "JWT")
    public List<Task> findAll(
            @RequestParam(name = AUTHOR, required = false) String author,
            @RequestParam(name = EXECUTOR, required = false) String executor,
            @RequestParam(name = PAGE, required = false, defaultValue = DEFAULT_PAGE) int page,
            @RequestParam(name = SIZE, required = false, defaultValue = DEFAULT_SIZE) int size
    ) {
        requestParametersValidator.paginationParamValid(page, size);
        return taskService.findAllWithFiltering(author, executor, page, size);
    }

    @GetMapping("/my")
    @ResponseStatus(HttpStatus.OK)
    @SecurityRequirement(name = "JWT")
    public List<Task> findMyTasks(
            @RequestParam(name = PAGE, required = false, defaultValue = DEFAULT_PAGE) int page,
            @RequestParam(name = SIZE, required = false, defaultValue = DEFAULT_SIZE) int size,
            Principal principal
    ) {
        return findAll(principal.getName(), null, page, size);
    }


    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    @SecurityRequirement(name = "JWT")
    public Task updateById(@PathVariable("id") int id,
                           @RequestBody TaskDto taskDto, Principal principal) {
        requestParametersValidator.idParamValid(id);

        return taskService.updateById(id, taskDto.toTask(), principal.getName());

    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    @SecurityRequirement(name = "JWT")
    public Task findById(@PathVariable("id") int id) {
        requestParametersValidator.idParamValid(id);
        return taskService.findById(id);
    }

    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "JWT")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    public void deleteById(@PathVariable("id") int id, Principal principal) {
        requestParametersValidator.idParamValid(id);
        taskService.checkAccessoryTask(principal.getName(), id);
        taskService.deleteById(id);
    }

}
