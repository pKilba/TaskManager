package com.kilbas.dto;

import com.sun.istack.NotNull;

public class TaskCommentDto {

    @NotNull
    private String email;
    @NotNull
    private String content;

    public TaskCommentDto(String email, String content) {
        this.email = email;
        this.content = content;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
