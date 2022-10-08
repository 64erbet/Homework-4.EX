package ua.goit.jdbc.controller;

import ua.goit.jdbc.command.Command;
import ua.goit.jdbc.exceprtions.ExitException;
import ua.goit.jdbc.view.View;

import java.util.List;

public class Storage {

    private final View view;
    private final List<Command> commands;

    public Storage(View view, List<Command> commands) {
        this.view = view;
        this.commands = commands;
    }

    public void run() {
        view.write("Привет :) А не ввести ли Вам \"Help\", чтоб увидеть весь список возможных неприятностей?");
        try {
            execute();
        } catch (ExitException e)
        {
            e.printStackTrace();
        }
    }

    private void execute() {
        while(true) {
            String input = view.read();
            boolean inputCorrect = false;
            for(Command command : commands) {
                if (command.canExecute(input)) {
                    try {
                        command.execute();
                    } catch (NumberFormatException e) {
                        view.write("******** Wrong input ******** ");
                    }
                    inputCorrect = true;
                }
            }
            if(!inputCorrect) {
                view.write("Такой комманды нет. Ведите, пожалуйста HELP чтобы увидеть список комманд");
            }
        }
    }
}
