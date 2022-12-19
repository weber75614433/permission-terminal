package com.chenyo.permission;

import com.chenyo.permission.logrecord.LogRecord;

public class Main implements Runnable{
    private static LogRecord logRecord;
    private static UserList userList;
    private static CommandList commandList;
    private static LoginFrame loginFrame;
    private static CommandProcessing commandProcessing;

    public static LogRecord getLogRecord(){
        return logRecord;
    }
    public static UserList getUserList(){
        return userList;
    }
    public static CommandList getCommandList(){
        return commandList;
    }

    public static LoginFrame getLoginFrame() {
        return loginFrame;
    }
    public static CommandProcessing getCommandProcessing() {
        return commandProcessing;
    }

    public Main(){
        logRecord = new LogRecord();
        userList = new UserList();
        commandList = new CommandList();
        commandProcessing = new CommandProcessing();
        loginFrame = new LoginFrame();
    }

    public static void main(String[] args){
        new Main().run();
        getLoginFrame().startFrame();

    }

    @Override
    public void run() {
        getUserList().run();
        getCommandList().run();
    }
}
