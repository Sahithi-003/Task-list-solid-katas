package com.codurance.training.tasks;

import com.codurance.training.tasks.module.Project;
import com.codurance.training.tasks.module.Task;
import com.codurance.training.tasks.module.TaskId;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TaskList {
    private final Map<Project, List<Task>> tasks = new LinkedHashMap<>();
    private long lastId = 0;
    public String show() {
        var writer = new StringWriter();
        var out = new PrintWriter(writer);
        for (Map.Entry<Project, List<Task>> projectListEntry : tasks.entrySet()) {
            out.println(projectListEntry.getKey().name());
            for (Task task : projectListEntry.getValue()) {
                out.print(task.toString());
            }
            out.println();
        }
        return writer.toString();
    }
    @Override
    public String toString() {
        var writer = new StringWriter();
        var out = new PrintWriter(writer);
        for (Map.Entry<Project, List<Task>> projectListEntry : tasks.entrySet()) {
            out.println(projectListEntry.getKey().name());
            for (Task task : projectListEntry.getValue()) {
                out.printf("    [%c] %s: %s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription());
            }
            out.println();
        }
        return writer.toString();
    }


    public String addProject(Project project) {
        tasks.put(project, new ArrayList<Task>());
        return "";
    }

    public String addTask(Project project, String description, String taskId) {
        var writer = new StringWriter();
        var out = new PrintWriter(writer);
        List<Task> projectTasks = tasks.get(project);
        if (projectTasks == null) {
            out.printf("Could not find a project with the name \"%s\".", project);
            out.println();
        }
        TaskId id;
        if(taskId == null){
            id = nextId();
        }
        else {
            id = new TaskId(taskId);
        }
        projectTasks.add(new Task(id, description, false));
        return writer.toString();
    }

    public void setDone(TaskId taskId, boolean done)throws TaskNotFoundException {
        Task task = findTaskById(taskId);
        task.setDone(done);
    }


    private TaskId nextId() {
        lastId++;
        return new TaskId(String.valueOf(lastId));
    }

    public void setDeadline(TaskId taskId, String deadLine) throws TaskNotFoundException{
       Task task = findTaskById(taskId);
       task.setDeadline(deadLine);
    }
    private Task findTaskById(TaskId taskId) throws TaskNotFoundException{
        for (Map.Entry<Project, List<Task>> projectListEntry : tasks.entrySet()) {
            for (Task task : projectListEntry.getValue()) {
                if (taskId.equals(task.getId())) {
                    return task;
                }
            }
        }
        throw new TaskNotFoundException(taskId);

    }

}
