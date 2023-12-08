package com.kilbas.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;


import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column()
    private String name;

    @Column()
    private String description;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusTask statusTask;

    @Enumerated(EnumType.STRING)
    @Column(name = "priority")
    private PriorityTask priorityTask;

    @Column
    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TaskComment> comments;

    public List<TaskComment> getComments() {
        return comments;
    }

    public void setComments(List<TaskComment> comments) {
        this.comments = comments;
    }

    @Column()
    private String author;
    @Column()
    private String executor;

    public Task() {

    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", statusTask=" + statusTask +
                ", priorityTask=" + priorityTask +
                ", author='" + author + '\'' +
                ", executor='" + executor + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        if (!super.equals(o)) return false;
        Task that = (Task) o;
        return getId() == that.getId()  && Objects.equals(getName(), that.getName()) && Objects.equals(getDescription(), that.getDescription()) && getStatusTask() == that.getStatusTask() && getPriorityTask() == that.getPriorityTask() && Objects.equals(getAuthor(), that.getAuthor()) && Objects.equals(getExecutor(), that.getExecutor()) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getId(), getName(), getDescription(), getStatusTask(), getPriorityTask(), getAuthor(), getExecutor());
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatusTask(StatusTask statusTask) {
        this.statusTask = statusTask;
    }

    public void setPriorityTask(PriorityTask priorityTask) {
        this.priorityTask = priorityTask;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setExecutor(String performer) {
        this.executor = performer;
    }



    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public StatusTask getStatusTask() {
        return statusTask;
    }

    public PriorityTask getPriorityTask() {
        return priorityTask;
    }

    public String getAuthor() {
        return author;
    }

    public String getExecutor() {
        return executor;
    }


    public Task(int id, String name, String description, StatusTask statusTask, PriorityTask priorityTask, String author, String executor) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.statusTask = statusTask;
        this.priorityTask = priorityTask;
        this.author = author;
        this.executor = executor;

    }


//
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
//    @Column(name = "create_date", nullable = false, updatable = false)
//    private Timestamp createDate;
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
//    @Column(name = "last_update_date", nullable = false)
//    private Timestamp lastUpdateDate;
}
