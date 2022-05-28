@ ECHO OFF
setlocal
rem
rem ******************************************************************************
rem Reserver Memory Application - Rohtash Singh
rem ******************************************************************************
rem
echo.  
echo Starting Reserver Memory Application ...
echo.  
rem

set JAVA_HOME=%JAVA_HOME%
set LIBS_HOME=.

set CLASSPATH=%JAVA_HOME%;%LIBS_HOME%/jvm.jar;
set CLASSPATH=%CLASSPATH%;%LIBS_HOME%/MemoryTest.jar;

java -cp %CLASSPATH% MemoryManager %*
endlocal
@echo on