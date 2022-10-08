package ua.goit.jdbc.model.dao;

import java.util.Date;

public class ProjectDao {
    private String projectNameDAO = "";
    private Integer customerIdDAO = -1;
    private Date createDateDAO = null;

    public ProjectDao(String projectNameDAO,
                      Integer customerIdDAO, Date createDateDAO) {
        this.projectNameDAO = projectNameDAO;
        this.customerIdDAO = customerIdDAO;
        this.createDateDAO = createDateDAO;
    }

    public String getProjectNameDAO() {
        return projectNameDAO;
    }

    public void setProjectNameDAO(String projectNameDAO) {
        this.projectNameDAO = projectNameDAO;
    }

    public Integer getCustomerIdDAO() {
        return customerIdDAO;
    }

    public void setCustomerIdDAO(Integer customerIdDAO) {
        this.customerIdDAO = customerIdDAO;
    }

    public Date getCreateDateDAO() {
        return createDateDAO;
    }

    public void setCreateDateDAO(Date createDateDAO) {
        this.createDateDAO = createDateDAO;
    }
}
