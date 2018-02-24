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

set JVM_HOME=..
set LIBS_HOME=.

set CLASSPATH=%JVM_HOME%
set CLASSPATH=%CLASSPATH%;%LIBS_HOME%
set CLASSPATH=%CLASSPATH%;%LIBS_HOME%/MemoryTest.jar;
set JVM_PROPERTIES=
set JVM_PROPERTIES=%JVM_PROPERTIES% -Xms64m
set JVM_PROPERTIES=%JVM_PROPERTIES% -Xmx96m
set JVM_PROPERTIES=%JVM_PROPERTIES% -width720
set JVM_PROPERTIES=%JVM_PROPERTIES% -height576
siege %JVM_PROPERTIES% -cp %CLASSPATH% MemoryManager %*
endlocal
@echo on
