package com.company.cpbm.entity;

import com.haulmont.bpm.entity.ProcInstance;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.security.entity.User;

import javax.annotation.PostConstruct;
import javax.persistence.*;

@NamePattern("%s|title")
@Table(name = "CPBM_TASK")
@Entity(name = "cpbm_Task")
public class Task extends StandardEntity {
    private static final long serialVersionUID = 1849245942285425616L;

    @Column(name = "NAME")
    protected String title;

    @Lob
    @Column(name = "DESCRIPTION")
    protected String description;

    @Column(name = "ACCEPTANCE_REQUIRED")
    protected Boolean acceptanceRequired;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "INITIATOR_ID")
    protected User initiator;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EXECUTOR_ID")
    protected User executor;

    @Column(name = "STATE")
    protected String state;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PROC_INSTANCE_ID")
    protected ProcInstance procInstance;

    public ProcInstance getProcInstance() {
        return procInstance;
    }

    public void setProcInstance(ProcInstance procInstance) {
        this.procInstance = procInstance;
    }

    public TaskState getState() {
        return state == null ? null : TaskState.fromId(state);
    }

    public void setState(TaskState state) {
        this.state = state == null ? null : state.getId();
    }

    public User getExecutor() {
        return executor;
    }

    public void setExecutor(User executor) {
        this.executor = executor;
    }

    public User getInitiator() {
        return initiator;
    }

    public void setInitiator(User initiator) {
        this.initiator = initiator;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @PostConstruct
    protected void initState() {
        if (getState() == null) {
            setState(TaskState.NEW);
        }
    }

    public Boolean getAcceptanceRequired() {
        return acceptanceRequired;
    }

    public void setAcceptanceRequired(Boolean acceptanceRequired) {
        this.acceptanceRequired = acceptanceRequired;
    }
}