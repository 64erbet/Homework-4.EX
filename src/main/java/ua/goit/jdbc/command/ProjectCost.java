package ua.goit.jdbc.command;

import ua.goit.jdbc.config.DatabaseManagerConnector;
import ua.goit.jdbc.view.View;

import java.math.BigDecimal;
import java.sql.*;
import java.util.Scanner;

public class ProjectCost implements Command{
    public static final String PROJECT_COST = "cost";
    private Integer projectId = -1;
    DatabaseManagerConnector connector;
    private final View view;

    public ProjectCost(View view, DatabaseManagerConnector connector) {
        this.connector = connector;
        this.view = view;
    }
    @Override
    public boolean canExecute(String input) {
        return input.equalsIgnoreCase(PROJECT_COST);
    }
    @Override
    public void execute() {
        view.write("Введите пожалуйста id проекта, сумму которого Вы хотите узнать");
        Integer enteredProjectId = Integer.parseInt(view.read());

        String QUERY = "SELECT p.projectid, SUM(d.salary) "
                + "FROM projects p "
                + "LEFT JOIN developers_projects dp "
                + "ON p.projectid = dp.dp_project_id "
                + "LEFT JOIN developers d "
                + "ON d.developerid = dp.dp_developer_id "
                + "WHERE p.projectid = "
                + enteredProjectId
                + " "
                + "GROUP BY p.projectid"
                ;

        try (Connection connection = connector.getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(QUERY);
            resultSet.next();
            System.out.println("Проект номер " + enteredProjectId + " стоит "
                    + resultSet.getObject(2, BigDecimal.class)
                    + " долларов");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
