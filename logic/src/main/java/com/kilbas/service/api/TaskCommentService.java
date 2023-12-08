package com.kilbas.service.api;

import com.kilbas.dto.TaskCommentDto;

public interface TaskCommentService {

  void  createTaskComment(int id, TaskCommentDto taskComment);

}
