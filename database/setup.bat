@echo off

docker exec -it fujiko-sm_database_1 /opt/mssql-tools/bin/sqlcmd -S localhost -U sa -P k1j4pe9f5ct -Q "CREATE DATABASE fujiko"

PAUSE