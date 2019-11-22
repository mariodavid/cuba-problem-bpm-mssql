-- begin CPBM_TASK
create table CPBM_TASK (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(255),
    DESCRIPTION longvarchar,
    ACCEPTANCE_REQUIRED boolean,
    INITIATOR_ID varchar(36),
    EXECUTOR_ID varchar(36),
    STATE varchar(50),
    PROC_INSTANCE_ID varchar(36),
    --
    primary key (ID)
)^
-- end CPBM_TASK
