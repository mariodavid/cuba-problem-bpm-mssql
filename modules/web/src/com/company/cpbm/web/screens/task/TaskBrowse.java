package com.company.cpbm.web.screens.task;

import com.haulmont.cuba.gui.screen.*;
import com.company.cpbm.entity.Task;

@UiController("cpbm_Task.browse")
@UiDescriptor("task-browse.xml")
@LookupComponent("tasksTable")
@LoadDataBeforeShow
public class TaskBrowse extends StandardLookup<Task> {
}