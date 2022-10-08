package ua.goit.jdbc.command;

import ua.goit.jdbc.config.DatabaseManagerConnector;
import ua.goit.jdbc.view.View;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JavaDevelopersList implements Command {

    public static final String JAVA_DEVS_LIST = "java_devs";
    DatabaseManagerConnector connector;
    private final View view;

    public JavaDevelopersList(View view, DatabaseManagerConnector databaseManagerConnector) {
        connector = databaseManagerConnector;
        this.view = view;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equalsIgnoreCase(JAVA_DEVS_LIST);
    }

    @Override
    public void execute() {

        String JAVA_DEVS_QUERY = "SELECT STRING_AGG(d.firstname, ', ') "
                + "FROM developers d "
                + "LEFT JOIN developers_skills ds "
                + "ON d.developerid = ds.ds_developer_id "
                + "LEFT JOIN skills s "
                + "ON s.skillid = ds.ds_skill_id "
                + "WHERE s.skillname = 'Java'"
                ;

        try (Connection connection = connector.getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(JAVA_DEVS_QUERY);
            resultSet.next();
            System.out.println("Список всех JAVA разрабов: "
                    + resultSet.getObject(1, String.class));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
