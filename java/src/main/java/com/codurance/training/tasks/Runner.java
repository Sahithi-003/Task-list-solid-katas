package com.codurance.training.tasks;

import com.codurance.training.tasks.command.Command;
import com.codurance.training.tasks.command.CommandFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public final class Runner implements Runnable {
    private static final String QUIT = "quit";


    private final BufferedReader in;
    private final PrintWriter out;


    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        new Runner(in, out).run();
    }

    public Runner(BufferedReader reader, PrintWriter writer) {
        this.in = reader;
        this.out = writer;
    }

    public void run() {
        TaskList taskList = new TaskList();
        while (true) {
            out.print("> ");
            out.flush();
            String commandLine;
            try {
                commandLine = in.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (commandLine.equals(QUIT)) {
                break;
            }
            Command command = CommandFactory.createCommand(commandLine);
            String result = command.execute(taskList);
            out.print(result);
        }
    }
}
