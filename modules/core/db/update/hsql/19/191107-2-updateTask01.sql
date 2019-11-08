alter table CPBM_TASK add constraint FK_CPBM_TASK_ON_INITIATOR foreign key (INITIATOR_ID) references SEC_USER(ID);
