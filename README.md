## CUBA Problem - BPM is Stuck

This repo is an example that shows a problem with the combination of the BPM addon. When referencing the ProcInstance from the business entity and loading the business entity in the process execution (that will also load the procInstance via a corresponding view) - the application breaks at the point where the process starts to run.

The error happens at least with the following DBMS:

* HSQLDB
* MSSQL


### Example description

The example is a Task management app. It contains of one Entity called `Task`. A task has the following attributes:

* Title
* Description
* Initiator (the user who created the Task)
* Executor (the assigned person who has to do the Job)
* State (can be: NEW, CONFIRMED, CLOSED)


A task can be created and persisted by the initiator of the task. 

![Task Details](https://github.com/mariodavid/cuba-problem-bpm-mssql/blob/master/img/task-details.png)

Once a Task is created, the Executor can open the Task and "Accept and start Working" the task by clicking the
corresponding button. With that a the Task Approval Process it triggered.  

### BPM Process Model

![BPM Process Model](https://github.com/mariodavid/cuba-problem-bpm-mssql/blob/master/img/bpm-process-model.png)

![Deployed Process Model](https://github.com/mariodavid/cuba-problem-bpm-mssql/blob/master/img/deployed-process-models.png)


### Requirements to setup example

1. Import the BPM process model [Task Approval](https://github.com/mariodavid/cuba-problem-bpm-mssql/blob/master/bpm-process-models/Task%20Approval.json) via `BPM > Process Models > Import`
2. Deploy the BPM process model by selecting the newly created instance and hit `Deploy`



### MSSQL branch

In the branch [mssql](https://github.com/mariodavid/cuba-problem-bpm-mssql/tree/mssql) the connection setting is set to
MSSQL.

It requires to have a running MS SQL server. Using the [MSSQL docker image](https://hub.docker.com/_/microsoft-mssql-server) 
it can use be created via the following command:

`docker run -e 'ACCEPT_EULA=Y' -e 'SA_PASSWORD=yourStrong(!)Password' -p 1433:1433 -d mcr.microsoft.com/mssql/server:2017-CU8-ubuntu`


### Problem example


#### BPM is Stuck with load of ProcInstance during process execution

![problem](/img/problem-with-view.gif)

#### BPM is not Stuck without load of ProcInstance during process execution
![no problem](/img/no-problem-without-view.gif)

