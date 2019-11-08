alter table CPBM_TASK add constraint FK_CPBM_TASK_ON_EXECUTOR foreign key (EXECUTOR_ID) references SEC_USER(ID);
