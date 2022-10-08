package ua.goit.jdbc;

//import ua.goit.jdbc.CRUD.Crud;
import ua.goit.jdbc.command.*;
import ua.goit.jdbc.config.DatabaseManagerConnector;
import ua.goit.jdbc.controller.Storage;
import ua.goit.jdbc.view.Console;
import ua.goit.jdbc.view.View;
//import ua.goit.jdbc.console.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        DatabaseManagerConnector connector = new DatabaseManagerConnector("localhost", 5455,
                "task_3", "postgres", "12345");

        Scanner scanner = new Scanner(System.in);
        View view = new Console(scanner);
        List<Command> commands = new ArrayList<>();
        commands.add(new Exit(view));
        commands.add(new Help(view));
        commands.add(new ProjectCost(view, connector));
        commands.add(new ProjectDevelopersList(view, connector));
        commands.add(new JavaDevelopersList(view, connector));
        commands.add(new MiddleDevelopersList(view, connector));
        commands.add(new FormattedProjectsList(view, connector));

        Storage storage = new Storage(view, commands);
        storage.run();
    }
}
