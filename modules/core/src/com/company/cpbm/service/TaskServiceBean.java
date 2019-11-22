package com.company.cpbm.service;

import com.company.cpbm.entity.Task;
import com.company.cpbm.entity.TaskState;
import com.haulmont.cuba.core.global.DataManager;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.UUID;

@Service(TaskService.NAME)
public class TaskServiceBean implements TaskService {

    @Inject
    protected DataManager dataManager;

    @Inject
    protected Logger log;

    @Override
    public void markTaskAsConfirmed(UUID taskId) {

        Task task = loadTask(taskId);

        if(task != null) {
            task.setState(TaskState.CONFIRMED);

            dataManager.commit(task);

            log.info("Task persisted with state: {}", task.getState());
        }
    }

    private Task loadTask(UUID taskId) {
        Task task = dataManager.load(Task.class)

                /*
                 loading the Task with a reference to the ProcInstance Table causes the application to fail
                 */
                .view("task-view")

                .id(taskId)
                .optional()
                .orElse(null);

        if(task != null)
            log.info("Task loaded with ID: {}", taskId);
        else
            log.error("No Task with ID {} available", taskId);

        return task;
    }
}