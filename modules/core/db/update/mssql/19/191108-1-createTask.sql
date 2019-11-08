create table CPBM_TASK (
    ID uniqueidentifier,
    VERSION integer not null,
    CREATE_TS datetime2,
    CREATED_BY varchar(50),
    UPDATE_TS datetime2,
    UPDATED_BY varchar(50),
    DELETE_TS datetime2,
    DELETED_BY varchar(50),
    --
    NAME varchar(255),
    DESCRIPTION varchar(max),
    ACCEPTANCE_REQUIRED tinyint,
    INITIATOR_ID uniqueidentifier,
    EXECUTOR_ID uniqueidentifier,
    STATE varchar(50),
    --
    primary key nonclustered (ID)
);