package pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.services;

import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.dto.task.TaskDTOCreate;

import javax.servlet.http.HttpServletRequest;

public interface TaskService {

    void createTask(TaskDTOCreate taskDTO, HttpServletRequest request);
}
