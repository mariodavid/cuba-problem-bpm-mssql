## CUBA Problem - BPM + MSSQL

This repo is an example that shows a problem with the combination of the BPM addon together with MSSQL database.

### Requirements to setup example

1. Import the BPM process model [Task Approval](https://github.com/mariodavid/cuba-problem-bpm-mssql/blob/master/bpm-process-models/Task%20Approval.json) via `BPM > Process Models > Import`
2. Deploy the BPM process model by selecting the newly created instance and hit `Deploy`



### MSSQL branch

In the branch [mssql](https://github.com/mariodavid/cuba-problem-bpm-mssql/tree/mssql) the connection setting is set to
MSSQL.

It requires to have a running MS SQL server. Using the [MSSQL docker image](https://hub.docker.com/_/microsoft-mssql-server) 
it can use be created via the following command:

`docker run -e 'ACCEPT_EULA=Y' -e 'SA_PASSWORD=yourStrong(!)Password' -p 1433:1433 -d mcr.microsoft.com/mssql/server:2017-CU8-ubuntu`

