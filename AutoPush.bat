@echo off
cd /d "%~dp0"

javac AutoPush.java

if errorlevel 1 (
    echo Compilation failed!
    pause
    exit /b
)

java -cp . AutoPush

pause