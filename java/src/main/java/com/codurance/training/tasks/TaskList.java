package com.codurance.training.tasks;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TaskList {
    private final Map<String, List<Task>> tasks = new LinkedHashMap<>();
    private TaskId lastId = new TaskId(0);
    String execute(String commandLine) {
        String[] commandRest = commandLine.split(" ", 2);
        String command = commandRest[0];
        switch (command) {
            case "show":
                return show();
            case "add":
                return add(commandRest[1]);
            case "check":
                return check(commandRest[1]);
            case "uncheck":
                return uncheck(commandRest[1]);
            case "help":
                return help();
            default:
                return error(command);
        }
    }

    private String show() {
        var writer = new StringWriter();
        var out = new PrintWriter(writer);
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            out.println(project.getKey());
            for (Task task : project.getValue()) {
                out.printf("    [%c] %s: %s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription());
            }
            out.println();
        }
        return writer.toString();
    }

    private String add(String commandLine) {
        var writer = new StringWriter();
        var out = new PrintWriter(writer);
        String[] subcommandRest = commandLine.split(" ", 2);
        String subcommand = subcommandRest[0];
        if (subcommand.equals("project")) {
            addProject(subcommandRest[1]);
        } else if (subcommand.equals("task")) {
            String[] projectTask = subcommandRest[1].split(" ", 2);
            addTask(projectTask[0], projectTask[1]);
        }
        return writer.toString();
    }

    private String addProject(String name) {
        tasks.put(name, new ArrayList<Task>());
        return "";
    }

    private String addTask(String project, String description) {
        var writer = new StringWriter();
        var out = new PrintWriter(writer);
        List<Task> projectTasks = tasks.get(project);
        if (projectTasks == null) {
            out.printf("Could not find a project with the name \"%s\".", project);
            out.println();
        }
        projectTasks.add(new Task(nextId(), description, false));
        return writer.toString();
    }

    private String check(String idString) {
        setDone(idString, true);
        return "";
    }

    private String uncheck(String idString) {
        setDone(idString, false);
        return "";
    }

    private String setDone(String idString, boolean done) {
        var writer = new StringWriter();
        var out = new PrintWriter(writer);
        int id = Integer.parseInt(idString);
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            for (Task task : project.getValue()) {
                if (task.getId().id() == id) {
                    task.setDone(done);
                }
            }
        }
        out.printf("Could not find a task with an ID of %d.", id);
        out.println();
        return writer.toString();
    }

    private String help() {
        var writer = new StringWriter();
        var out = new PrintWriter(writer);
        out.println("Commands:");
        out.println("  show");
        out.println("  add project <project name>");
        out.println("  add task <project name> <task description>");
        out.println("  check <task ID>");
        out.println("  uncheck <task ID>");
        out.println();
        return writer.toString();
    }

    private String error(String command) {
        var writer = new StringWriter();
        var out = new PrintWriter(writer);
        out.printf("I don't know what the command \"%s\" is.", command);
        out.println();
        return writer.toString();
    }

    private TaskId nextId() {
        lastId = new TaskId(lastId.id() + 1);
        return lastId;
    }
}
