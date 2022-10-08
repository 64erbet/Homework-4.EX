package ua.goit.jdbc.repository;

import ua.goit.jdbc.config.DatabaseManagerConnector;
import ua.goit.jdbc.model.dao.ProjectDao;
import ua.goit.jdbc.model.dto.ProjectDto;

import java.util.List;

public class ProjectRepository implements Repository<ProjectDao>{

    private final DatabaseManagerConnector connector;

    public ProjectRepository(DatabaseManagerConnector connector) {
        this.connector = connector;
    }


    @Override
    public ProjectDao save(ProjectDao entity) {
        return entity;
    }

    @Override
    public void delete(ProjectDao entity) {

    }

    @Override
    public ProjectDao findById(int id) {
        return null;
    }

    @Override
    public List<ProjectDao> findAll() {
        return null;
    }
}
