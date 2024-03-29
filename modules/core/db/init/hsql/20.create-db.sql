-- begin CPBM_TASK
alter table CPBM_TASK add constraint FK_CPBM_TASK_ON_INITIATOR foreign key (INITIATOR_ID) references SEC_USER(ID)^
alter table CPBM_TASK add constraint FK_CPBM_TASK_ON_EXECUTOR foreign key (EXECUTOR_ID) references SEC_USER(ID)^
alter table CPBM_TASK add constraint FK_CPBM_TASK_ON_PROC_INSTANCE foreign key (PROC_INSTANCE_ID) references BPM_PROC_INSTANCE(ID)^
create index IDX_CPBM_TASK_ON_INITIATOR on CPBM_TASK (INITIATOR_ID)^
create index IDX_CPBM_TASK_ON_EXECUTOR on CPBM_TASK (EXECUTOR_ID)^
create index IDX_CPBM_TASK_ON_PROC_INSTANCE on CPBM_TASK (PROC_INSTANCE_ID)^
-- end CPBM_TASK
