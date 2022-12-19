package com.chenyo.permission.perms;

import com.chenyo.permission.Main;
import org.yaml.snakeyaml.Yaml;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.*;

public class Basic implements SetUserPerms{
    public static ArrayList<String> permsBasic = new ArrayList<>();
    public File file = new File("./src/main/resources/permsBASIC.yml");
    public Yaml yaml = new Yaml();

    @Override
    public void Extends(){
        permsBasic.addAll(UserPerms.getAdvanced().permsAdvanced);
    }

    @Override
    public Boolean CheckList(String user){
        return permsBasic.contains(user);
    }

    public void ImportBASICYml(){

        try {
            if(file.exists() && !file.isDirectory()){

                InputStream inputStream = Files.newInputStream(file.toPath());
                permsBasic = yaml.load(inputStream);
                Main.getLogRecord().setINFO("permsBASIC import success");
            }else{
                permsBasic.add("Weber");
                PrintWriter printWriter = new PrintWriter(
                        new File("./src/main/resources/permsBASIC.yml"));
                yaml.dump(permsBasic, printWriter);
                Main.getLogRecord().setINFO("permsBASIC Establish success");
            }
        }catch (Exception e){
            e.printStackTrace();
            Main.getLogRecord().setError("ERROR : " + e);
        }
    }

    public void UploadBASICYml(){
        Main.getLogRecord().setINFO("permsBASIC Upload success");
        try {
            PrintWriter printWriter = new PrintWriter(
                    new File("./src/main/resources/permsBASIC.yml"));
            yaml.dump(permsBasic, printWriter);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Main.getLogRecord().setError("ERROR : " + e);
        }
    }

}
