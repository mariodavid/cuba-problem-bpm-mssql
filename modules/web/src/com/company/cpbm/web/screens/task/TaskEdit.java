package com.company.cpbm.web.screens.task;

import com.company.cpbm.entity.Task;
import com.haulmont.bpm.entity.ProcInstance;
import com.haulmont.bpm.service.BpmEntitiesService;
import com.haulmont.bpm.service.ProcessRuntimeService;
import com.haulmont.cuba.core.global.EntityStates;
import com.haulmont.cuba.gui.Dialogs;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.app.core.inputdialog.InputDialog;
import com.haulmont.cuba.gui.components.Action;
import com.haulmont.cuba.gui.screen.*;
import com.haulmont.cuba.security.global.UserSession;

import javax.inject.Inject;
import java.util.HashMap;

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
    protected UserSession userSession;
    @Inject
    private MessageBundle messageBundle;

    @Inject
    private Notifications notifications;

    @Subscribe
    protected void onInitEntity(InitEntityEvent<Task> event) {
        event.getEntity().setInitiator(userSession.getCurrentOrSubstitutedUser());
    }


    @Subscribe("acceptAndConfirm")
    protected void onAcceptAndConfirm(Action.ActionPerformedEvent event) {


        commitChanges()
                .then(() -> {
                    dialogs.createInputDialog(this)
                            .withCaption(messageBundle.getMessage("acceptAndStartWorking"))
                            .withParameter(
                                    stringParameter("comment")
                                            .withCaption(messageBundle.getMessage("comment"))
                            )
                            .withCloseListener(closeEvent -> {
                                if (closeEvent.getCloseAction().equals(InputDialog.INPUT_DIALOG_OK_ACTION)) {
                                    String comment = closeEvent.getValue("comment");
                                    startProcessWithComment(comment);
                                }
                            }).show();
                })
                .then(() -> getScreenData().loadAll());
    }


    private void startProcessWithComment(String comment) {
        ProcInstance procInstance = createProcInstance(getEditedEntity());

        processRuntimeService.startProcess(
                procInstance,
                comment,
                new HashMap<>()
        );

        notifications.create()
                .withCaption(messageBundle.getMessage("taskAcceptedAndConfirmed"))
                .withType(Notifications.NotificationType.HUMANIZED)
                .show();
    }


    private ProcInstance createProcInstance(Task task) {
        BpmEntitiesService.ProcInstanceDetails procInstanceDetails = getProcInstanceDetails(task);

        procInstanceDetails.setEntity(task);

        return bpmEntitiesService.createProcInstance(procInstanceDetails);
    }

    private BpmEntitiesService.ProcInstanceDetails getProcInstanceDetails(Task task) {

        return new BpmEntitiesService.ProcInstanceDetails(PROCESS_CODE)
                .addProcActor("initiator", task.getInitiator())
                .addProcActor("executor", task.getExecutor())
                .setEntity(task);
    }


}