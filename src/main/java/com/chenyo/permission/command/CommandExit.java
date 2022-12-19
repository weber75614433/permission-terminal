package com.chenyo.permission.command;

import com.chenyo.permission.*;
import com.chenyo.permission.logrecord.LogRecord;
import com.chenyo.permission.terminal.Terminal;
import java.io.FileNotFoundException;
import java.util.concurrent.TimeUnit;

public class CommandExit implements Runnable , PermsVerify{
    public String showResult = "finish in 1 sec";
    private final String perms = setBasicPerms();

    @Override
    public void ConfirmPerms(){
            run();
    }

    public void run() {
        System.out.println("exit");
        String getCommand = LoginFrame.getTerminal().command;
        try {
            if(getCommand.equals("exit")){
                Main.getLogRecord().setINFO(Main.getLoginFrame().user + " exit");
                Main.getUserList().uploadYml();
                TimeUnit.SECONDS.sleep(3);
                System.exit(0);
            }
        } catch (InterruptedException | FileNotFoundException e) {
            e.printStackTrace();
            Main.getLogRecord().setError("Error : " + e);
        }

    }
}
