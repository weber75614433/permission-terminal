package com.chenyo.permission.command;

import com.chenyo.permission.LoginFrame;
import com.chenyo.permission.Main;

public class CommandAddUser implements Runnable, PermsVerify{

    private final String perms = setBasicPerms();
    @Override
    public void ConfirmPerms(){
        if(LoginFrame.getUserPerms().ComparePerms(Main.getLoginFrame().user, perms)){
            Main.getCommandProcessing().result = ("perms qualified");
            run();
        }else{
            Main.getLogRecord().setWarning(" :  UserPerms unqualified");
            Main.getCommandProcessing().result = ("perms unqualified");
        }
    }

    public void run() {
        System.out.println("Adduser");
        String[] subs = LoginFrame.getTerminal().command.split(" ");
        try {
           if(subs.length < 3){
               Main.getLogRecord().setWarning(LoginFrame.getTerminal().command + " : failed" + " -> information incomplete : empty password");
               Main.getCommandProcessing().result = "adduser failed -> empty password";
           }else if(subs.length == 3 & Main.getUserList().NameList.containsKey(subs[1])){
               Main.getLogRecord().setWarning(LoginFrame.getTerminal().command + " : failed" + " -> this userID is exist");
               Main.getCommandProcessing().result = " adduser failed -> userID is exist";
           }else if(subs.length == 3){
               Main.getUserList().AddUser(subs[1], subs[2]);
               Main.getLogRecord().setINFO(" : adduser success");
               Main.getCommandProcessing().result = "adduser success";
           }
        }catch (Exception e){
            e.printStackTrace();
            Main.getLogRecord().setError("Error : " + e);
        }

    }
}
