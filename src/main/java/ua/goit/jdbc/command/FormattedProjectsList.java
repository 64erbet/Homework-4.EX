package ua.goit.jdbc.command;

import ua.goit.jdbc.config.DatabaseManagerConnector;
import ua.goit.jdbc.view.View;

import java.sql.*;

public class FormattedProjectsList implements Command {
    public static final String FORMATTED_PROJECTS_LIST = "f_list";
    DatabaseManagerConnector connector;
    View view;

    public FormattedProjectsList(View view, DatabaseManagerConnector databaseManagerConnector) {
        connector = databaseManagerConnector;
        this.view = view;
    }
    @Override
    public boolean canExecute(String input) {
        return (input.equalsIgnoreCase(FORMATTED_PROJECTS_LIST));
    }

    @Override
    public void execute() {
        String MIDDLE_DEVS_QUERY = "SELECT p.createdate, p.projectname, COUNT(d.firstname) "
                + "FROM projects p "
                + "LEFT JOIN developers_projects dp "
                + "ON p.projectid = dp.dp_project_id "
                + "LEFT JOIN developers d "
                + "ON dp.dp_developer_id = d.developerid "
                + "GROUP BY p.projectname, p.createdate"
                ;

        try (Connection connection = connector.getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(MIDDLE_DEVS_QUERY);
            System.out.println("Список проектов в формате \n" +
                    "Дата создания      Название      Колличество разрабов: ");
            while (resultSet.next()) {
                System.out.println("  " + resultSet.getObject(1, Date.class) + "       "
                        + resultSet.getObject(2, String.class) + "               "
                        + resultSet.getInt(3)
                        );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
