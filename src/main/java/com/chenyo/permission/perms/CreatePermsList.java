package com.chenyo.permission.perms;

import java.util.Set;

public class CreatePermsList {
    public final String perms;
    public final SetUserPerms setUserPerms;

    public CreatePermsList(String perms, SetUserPerms setUserPerms){
        this.perms = perms;
        this.setUserPerms = setUserPerms;
    }

}
