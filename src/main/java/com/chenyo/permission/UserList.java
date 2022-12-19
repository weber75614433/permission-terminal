package com.chenyo.permission;

import org.yaml.snakeyaml.Yaml;
import java.io.*;
import java.nio.file.Files;
import java.util.HashMap;

public class UserList implements Runnable{

    public HashMap<String , String> NameList = new HashMap<String, String>();
    public File fIle = new File("./src/main/resources/NameList.yml");
    private final Yaml yaml = new Yaml();
    public void AddUser(String user, String perms){
        NameList.put(user, perms);
    }
    public void DelUser(String user){
        NameList.remove(user);
    }

    public void importYml(){
        try{
            if(fIle.exists() && !fIle.isDirectory()){

                InputStream inputStream = Files.newInputStream(fIle.toPath());
                NameList = yaml.load(inputStream);

            }else{
                NameList.put("Weber", "Hd83714433");
                NameList.put("Danny", "AB123456");
                NameList.put("Admin", "admin");
                PrintWriter printWriter = new PrintWriter(
                        new File("./src/main/resources/NameList.yml"));
                yaml.dump(NameList, printWriter);

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void uploadYml() throws FileNotFoundException {

        PrintWriter printWriter = new PrintWriter(
                new File("./src/main/resources/NameList.yml")
        );
        yaml.dump(NameList, printWriter);
    }

    public void AddAccount(String userID, String password) throws FileNotFoundException {
        NameList.put(userID, password);
        uploadYml();
    }

    public Boolean ComparePassword(String password){
       return password.hashCode() == NameList.get(Main.getLoginFrame().user).hashCode();
    }

    @Override
    public void run() {
        importYml();
    }
}
