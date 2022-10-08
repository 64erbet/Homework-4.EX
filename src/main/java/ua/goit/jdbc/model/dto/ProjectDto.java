package ua.goit.jdbc.model.dto;

import java.util.Date;

public class ProjectDto {

    private String projectNameDTO = "";
    private Integer customerIdDTO = -1;
    private Date createDateDTO = null;

    public ProjectDto(String projectNameDTO, Integer customerIdDTO, Date createDateDTO) {
        this.projectNameDTO = projectNameDTO;
        this.customerIdDTO = customerIdDTO;
        this.createDateDTO = createDateDTO;
    }

    public String getProjectNameDTO() {
        return projectNameDTO;
    }

    public void setProjectNameDTO(String projectNameDTO) {
        this.projectNameDTO = projectNameDTO;
    }

    public Integer getCustomerIdDTO() {
        return customerIdDTO;
    }

    public void setCustomerIdDTO(Integer customerIdDTO) {
        this.customerIdDTO = customerIdDTO;
    }

    public Date getCreateDateDTO() {
        return createDateDTO;
    }

    public void setCreateDateDTO(Date createDateDTO) {
        this.createDateDTO = createDateDTO;
    }
}
