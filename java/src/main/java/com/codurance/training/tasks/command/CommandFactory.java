package com.codurance.training.tasks.command;

public class CommandFactory {
    public static Command createCommand(String commandLine){
        String[] commandRest = commandLine.split(" ", 2);
        String command = commandRest[0];
        switch (command) {
            case "show":
                return new ShowCommand();
            case "add":
                return new AddCommand(commandRest[1]);
            case "check":
                return new CheckCommand(commandRest[1]);
            case "uncheck":
                return new UncheckCommand(commandRest[1]);
            case "help":
                return new HelpCommand();
            case "deadline":
                return new DeadLineCommand(command);
            default:
                return new ErrorCommand(command);
        }
    }
}
