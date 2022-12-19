package com.chenyo.permission.perms;

import java.util.List;

public interface SetUserPerms {
    public default Boolean CheckList(String user){
        return true;
    }
    public default void Extends(){

    }

}
