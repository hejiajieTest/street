@title gisportal

@REM *************************************************************************
@REM This script is used to start gisportal.
@REM 
@REM System will load all jar-packages in 'lib' directory
@REM *************************************************************************


@REM call setEnv.cmd here
@call "setEnv.cmd" %*
@echo .

@set JAVA_OPTIONS="-Dpaas.classpath=../lib"
@echo %JAVA_OPTIONS%


@REM Set debug options
set DEBUG_PORT=5192
set JAVA_DEBUG=-Xdebug -Xnoagent -Xrunjdwp:transport=dt_socket,address=%DEBUG_PORT%,server=y,suspend=n
java -cp ../resources;../lib/startup.jar %JAVA_OPTIONS% %JAVA_DEBUG% com.tscloud.common.main.StartUp gisportal
@pause
