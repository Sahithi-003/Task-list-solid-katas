package com.codurance.training.tasks.command;

import com.codurance.training.tasks.TaskList;
import com.codurance.training.tasks.module.Project;

public class AddCommand implements Command {
    private String command;
    public AddCommand(String command) {
        this.command=command;
    }

    @Override
    public String execute(TaskList taskList) {
        String[] subcommandRest = command.split(" ", 2);
        String subcommand = subcommandRest[0];
        if (subcommand.equals("project")) {
            taskList.addProject(new Project(subcommandRest[1]));
        } else if (subcommand.startsWith("task")) {
            String taskId = null;
            String[] projectTask = subcommandRest[1].split(" ", 2);
            Project project = new Project(projectTask[0]);
            String task = projectTask[1];
            if (subcommandRest[0].equals("task")){

            }else {
                taskId = subcommandRest[0].substring(5 , subcommandRest[0].length() - 1);
            }
            taskList.addTask(project, task, taskId);
        }
        return "";
    }
}
