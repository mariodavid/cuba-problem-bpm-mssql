alter table CPBM_TASK alter column EXECUTOR_ID rename to EXECUTOR_ID__U65781 ^
alter table CPBM_TASK drop constraint FK_CPBM_TASK_ON_EXECUTOR ;
drop index IDX_CPBM_TASK_ON_EXECUTOR ;
alter table CPBM_TASK alter column INITIATOR_ID rename to INITIATOR_ID__U20968 ^
alter table CPBM_TASK drop constraint FK_CPBM_TASK_ON_INITIATOR ;
drop index IDX_CPBM_TASK_ON_INITIATOR ;
alter table CPBM_TASK add column INITIATOR_ID varchar(36) ;
alter table CPBM_TASK add column EXECUTOR_ID varchar(36) ;
