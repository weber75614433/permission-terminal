package com.chenyo.permission.perms;


import java.util.HashMap;


public class UserPerms implements Runnable{
    private static Basic basic;
    private static Advanced advanced;
    private static Admin admin;

    public static Basic getBasic(){
        return basic;
    }
    public static Advanced getAdvanced() {
        return advanced;
    }
    public static Admin getAdmin() {
        return admin;
    }

    public HashMap<String, CreatePermsList> UserPermsList = new HashMap<>();

    public void setUserPermsList(){
        UserPermsList.put("basic", new CreatePermsList("basic", new Basic()));
        UserPermsList.put("advanced", new CreatePermsList("advanced", new Advanced()));
        UserPermsList.put("admin", new CreatePermsList("admin", new Admin()));

        basic = new Basic();
        advanced = new Advanced();
        admin = new Admin();
    }

    public Boolean ComparePerms(String user, String commandPerms){
        CreatePermsList createPermsList = UserPermsList.get(commandPerms);
        return createPermsList.setUserPerms.CheckList(user);
    }

    @Override
    public void run() {
        setUserPermsList();
    }

    public void getPermsYml(){
        getBasic().ImportBASICYml();
        getAdvanced().ImportADVANCEDYml();
        getAdmin().ImportADMINYml();
    }

    public void uploadStatus(){
        getAdmin().UploadADMINYml();
        getAdvanced().UploadADVANCEDYml();
        getBasic().UploadBASICYml();
    }
}
