package com.company.cpbm.web.screens.task;

import com.company.cpbm.entity.Task;
import com.haulmont.bpm.entity.ProcActor;
import com.haulmont.bpm.entity.ProcInstance;
import com.haulmont.bpm.gui.procactionsfragment.ProcActionsFragment;
import com.haulmont.bpm.service.BpmEntitiesService;
import com.haulmont.bpm.service.ProcessRuntimeService;
import com.haulmont.cuba.core.global.Messages;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.gui.Dialogs;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.app.core.inputdialog.InputDialog;
import com.haulmont.cuba.gui.app.core.inputdialog.InputParameter;
import com.haulmont.cuba.gui.components.Action;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.model.InstanceLoader;
import com.haulmont.cuba.gui.screen.*;
import com.haulmont.cuba.gui.util.OperationResult;
import com.haulmont.cuba.security.entity.User;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static com.haulmont.cuba.gui.app.core.inputdialog.InputParameter.*;

@UiController("cpbm_Task.edit")
@UiDescriptor("task-edit.xml")
@EditedEntityContainer("taskDc")
@LoadDataBeforeShow
public class TaskEdit extends StandardEditor<Task> {

    public static final String PROCESS_CODE = "taskApproval";


    @Inject
    protected BpmEntitiesService bpmEntitiesService;

    @Inject
    protected ProcessRuntimeService processRuntimeService;

    @Inject
    protected Dialogs dialogs;

    @Inject
    private MessageBundle messageBundle;

    @Inject
    private Notifications notifications;

    @Subscribe("startProcess")
    protected void onStartProcess(Action.ActionPerformedEvent event) {
        dialogs.createInputDialog(this)
                .withCaption(messageBundle.getMessage("startProcess"))
                .withParameter(
                        stringParameter("comment")
                        .withCaption(messageBundle.getMessage("comment"))
                )
                .withCloseListener(closeEvent -> {
                    if (closeEvent.getCloseAction().equals(InputDialog.INPUT_DIALOG_OK_ACTION)) {
                        startProcessWithComment(closeEvent.getValue("comment"));
                    }
                }).show();

    }

    private void startProcessWithComment(String comment) {
        ProcInstance procInstance = createProcInstance(getEditedEntity());

        processRuntimeService.startProcess(
                procInstance,
                comment,
                new HashMap<>()
        );
        notifications.create()
                .withCaption(messageBundle.getMessage("processStarted"))
                .withType(Notifications.NotificationType.HUMANIZED)
                .show();
    }


    private ProcInstance createProcInstance(Task task) {
        BpmEntitiesService.ProcInstanceDetails procInstanceDetails = getProcInstanceDetails(task);
        procInstanceDetails.setEntity(task);
        return bpmEntitiesService.createProcInstance(procInstanceDetails);
    }

    private BpmEntitiesService.ProcInstanceDetails getProcInstanceDetails(Task task) {
        BpmEntitiesService.ProcInstanceDetails procInstanceDetails = new BpmEntitiesService.ProcInstanceDetails(PROCESS_CODE)
                .setEntity(getEditedEntity());

        return procInstanceDetails;
    }


}