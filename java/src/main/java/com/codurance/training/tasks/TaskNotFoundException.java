package com.codurance.training.tasks;

import com.codurance.training.tasks.module.TaskId;

public class TaskNotFoundException extends Throwable {
    public TaskNotFoundException(TaskId taskId){
        super(String.format("Could not find a task with an ID of %s.", taskId));
    }
}
