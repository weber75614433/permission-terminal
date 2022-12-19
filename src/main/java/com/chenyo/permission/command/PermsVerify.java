package com.chenyo.permission.command;

public interface PermsVerify {
    String basicPerms = "basic";
    String advancedPerms = "advanced";
    void ConfirmPerms();
    default String setBasicPerms(){
        return basicPerms;
    }
    default String setAdvancedPerms(){
        return advancedPerms;
    }

}
