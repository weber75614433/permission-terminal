package com.chenyo.permission;

import com.chenyo.permission.command.CommandLine;
import com.chenyo.permission.command.CommandLogout;
import com.chenyo.permission.logrecord.LogRecord;
import com.chenyo.permission.terminal.Terminal;

public class CommandProcessing implements Runnable{
    public String result;

    public String getResult(){
        return result;
    }

    public void run() {
        String[] subs = LoginFrame.getTerminal().command.split(" ");
        CommandLine commandLine = Main.getCommandList().TerminalCommandList.get(subs[0]);
        if(commandLine == null){
            result = "no such command";
            Main.getLogRecord().setWarning(Main.getLoginFrame().user + " : " + LoginFrame.getTerminal().command + " -> no such command");
        }else{
            try{
                commandLine.permsVerify.ConfirmPerms();
                Main.getLogRecord().setINFO(Main.getLoginFrame().user + " : " + LoginFrame.getTerminal().command);
            }catch (Throwable throwable){
                throwable.printStackTrace();
            }
        }
    }
}
