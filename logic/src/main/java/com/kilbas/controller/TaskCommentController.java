package com.kilbas.controller;

import com.kilbas.dto.TaskCommentDto;
import com.kilbas.service.api.TaskCommentService;
import com.kilbas.service.api.TaskService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@Tag(name="Комментарии", description="контроллер для комментариев")
@RestController
@RequestMapping("/comments/{taskId}")
public class TaskCommentController {

    private final TaskCommentService taskCommentService;

    public TaskCommentController(TaskCommentService taskCommentService) {
        this.taskCommentService = taskCommentService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @SecurityRequirement(name = "JWT")
    public void createTaskComment(
            @PathVariable("taskId") int taskId,
            @RequestBody TaskCommentDto requestDTO) {
        taskCommentService.createTaskComment(taskId, requestDTO);
    }

}
