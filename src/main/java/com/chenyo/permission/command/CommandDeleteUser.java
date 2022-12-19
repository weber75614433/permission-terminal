package com.chenyo.permission.command;

import com.chenyo.permission.CommandProcessing;
import com.chenyo.permission.LoginFrame;
import com.chenyo.permission.Main;
import com.chenyo.permission.UserList;
import com.chenyo.permission.logrecord.LogRecord;
import com.chenyo.permission.perms.UserPerms;
import com.chenyo.permission.terminal.Terminal;

public class CommandDeleteUser implements Runnable, PermsVerify{
    private final String perms = setAdvancedPerms();
    @Override
    public void ConfirmPerms() {
        if(LoginFrame.getUserPerms().ComparePerms(Main.getLoginFrame().user, perms)){
            Main.getLogRecord().setINFO("perms qualified");
            Main.getCommandProcessing().result = "perms qualified";
            run();
        }else{
            Main.getLogRecord().setWarning(LoginFrame.getTerminal().command + " : UserPerms unqualified");
            Main.getCommandProcessing().result = "perms unqualified";
        }
    }

    @Override
    public void run() {
        String[] subs = LoginFrame.getTerminal().command.split(" ");
        try{
            if(subs.length <= 1){
                Main.getLogRecord().setWarning(LoginFrame.getTerminal().command + " : user can't be null");
                Main.getCommandProcessing().result = "user can't be null";

            }else if(subs.length == 2){
                if(!Main.getUserList().NameList.containsKey(subs[1])){
                    Main.getLogRecord().setWarning(LoginFrame.getTerminal().command + " : no such user");
                    Main.getCommandProcessing().result = "no such user";
                }else if(Main.getUserList().NameList.get(subs[1]).contains("admin")){
                    Main.getLogRecord().setWarning(LoginFrame.getTerminal().command + " : admin can't be edit");
                    Main.getCommandProcessing().result = "admin can't be edit";
                }else{
                    Main.getUserList().DelUser(subs[1]);
                    Main.getLogRecord().setINFO(LoginFrame.getTerminal().command + " : delete user success");
                    Main.getCommandProcessing().result = "delete user success";
                    Main.getUserList().uploadYml();
                }
            }

        }catch (Exception e){
            e.printStackTrace();
            Main.getLogRecord().setError("ERROR : " + e);
        }
    }
}
