::===========================================================================
:: Task 6 - Java 8 API
:: Execute from project's root folder
:: This is for building project and placing it into newly created directory /dist
:: together with all source files and uber-jar
:: Java archive is not created if any test failed or code coverage not been met.
::===========================================================================
@ECHO OFF
CALL mvnw.cmd clean package 2> nul
mkdir dist
set destination=%~dp0\dist
xcopy /-y Java8ApiApplication.cmd %destination%
xcopy /-y Java8ApiApplication.sh %destination%
xcopy /-y _README.md %destination%
xcopy /-y %~dp0\target\Java8ApiApplication.jar %destination%
EXIT /B
