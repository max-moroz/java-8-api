::=============================================================
:: Task 6 - Java 8 API
:: This should be at the same folder as Java8ApiApplication.jar
:: This is for executing Java8ApiApplication application
::=============================================================

@ECHO OFF
set file_name=Java8ApiApplication.jar

:: Check if .jar file available
IF NOT EXIST %~dp0%file_name% (
ECHO Error: Java8ApiApplication.jar is not found
EXIT /B 2
)


java -jar %~dp0%file_name% %*

