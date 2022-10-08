package ua.goit.jdbc.command;

import ua.goit.jdbc.config.DatabaseManagerConnector;
import ua.goit.jdbc.view.View;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MiddleDevelopersList implements Command {
    public static final String MIDDLE_DEVS_LIST = "middle_devs";
    DatabaseManagerConnector connector;
    View view;

    public MiddleDevelopersList(View view, DatabaseManagerConnector databaseManagerConnector) {
        connector = databaseManagerConnector;
        this.view = view;
    }
    @Override
    public boolean canExecute(String input) {
//        return false;
        return (input.equalsIgnoreCase(MIDDLE_DEVS_LIST));
    }

    @Override
    public void execute() {
        String MIDDLE_DEVS_QUERY = "SELECT STRING_AGG(DISTINCT d.firstname, ', ') "
                + "FROM skills s "
                + "LEFT JOIN developers_skills ds "
                + "ON s.skillid = ds.ds_skill_id "
                + "LEFT JOIN developers d "
                + "ON d.developerid = ds.ds_developer_id "
                + "WHERE s.skilllavel = 'Middle'"
                ;

        try (Connection connection = connector.getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(MIDDLE_DEVS_QUERY);
            resultSet.next();
            System.out.println("Список всех MIDDLE разрабов: "
                    + resultSet.getObject(1, String.class));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
