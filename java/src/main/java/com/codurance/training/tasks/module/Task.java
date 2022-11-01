package com.codurance.training.tasks.module;


public final class Task {
    private final TaskId id;
    private final String description;
    private boolean done;
    private String deadLine;

    public Task(TaskId id, String description, boolean done) {
        this.id = id;
        this.description = description;
        this.done = done;
    }

    public TaskId getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public void setDeadline(String deadLine) {
        this.deadLine = deadLine;
    }
    @Override
    public String toString(){
        String deadlineLabel = deadLine != null ? "(due "+deadLine + ")" : "";
        return String.format("    [%c] %s: %s%n", (done ? 'x' : ' '), id, description, deadlineLabel);
    }
}

