package com.chenyo.permission;

import com.chenyo.permission.command.*;

import java.util.HashMap;

public class CommandList implements Runnable{

    public HashMap<String, CommandLine> TerminalCommandList = new HashMap<String, CommandLine>();

    public void setTerminalCommandList(){
        TerminalCommandList.put("add", new CommandLine("add", "basic", new CommandAddUser()));
        TerminalCommandList.put("exit", new CommandLine("exit", "basic", new CommandExit()));
        TerminalCommandList.put("del", new CommandLine("del", "advanced", new CommandDeleteUser()));
        TerminalCommandList.put("logout", new CommandLine("logout", "basic", new CommandLogout()));
    }

    @Override
    public void run() {
        setTerminalCommandList();
    }
}
