package ua.goit.jdbc.command;

import ua.goit.jdbc.config.DatabaseManagerConnector;
import ua.goit.jdbc.view.View;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProjectDevelopersList implements Command {

    public static final String PROJECT_DEVS_LIST = "devs";
    private Integer projectId;
    DatabaseManagerConnector connector;
    private final View view;

    public ProjectDevelopersList(View view, DatabaseManagerConnector databaseManagerConnector) {
        connector = databaseManagerConnector;
        this.view = view;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equalsIgnoreCase(PROJECT_DEVS_LIST);
    }

    @Override
    public void execute() {
        view.write("Введите пожалуйста id проекта, список разрабов которого Вы хотите узнать");
        Integer enteredProjectId = Integer.parseInt(view.read());

        String QUERY = "SELECT p.projectid, STRING_AGG(d.firstname, ', ') "
                + "FROM projects p "
                + "LEFT JOIN developers_projects dp "
                + "ON p.projectid = dp.dp_project_id "
                + "LEFT JOIN developers d "
                + "ON dp.dp_developer_id = d.developerid "
                + "WHERE p.projectid = "
                + enteredProjectId
                + " "
                + "GROUP BY p.projectid"
        ;

        try (Connection connection = connector.getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(QUERY);
            resultSet.next();
            System.out.println("Список разрабов проекта "
                    + enteredProjectId
                    + " : "
                    + resultSet.getObject(2, String.class));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
