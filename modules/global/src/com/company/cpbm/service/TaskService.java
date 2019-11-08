package com.company.cpbm.service;

import java.util.UUID;
import com.company.cpbm.entity.Task;

public interface TaskService {
    String NAME = "cpbm_TaskService";

    void markTaskAsConfirmed(UUID taskId);
}