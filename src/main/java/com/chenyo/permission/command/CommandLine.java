package com.chenyo.permission.command;

public class CommandLine {
    public final String command;
    public final String perms;
    public final PermsVerify permsVerify;


    public CommandLine(String command, String perms, PermsVerify permsVerify) {
        this.command = command;
        this.perms = perms;
        this.permsVerify = permsVerify;
    }
}
