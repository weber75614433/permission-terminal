package com.chenyo.permission.perms;

import com.chenyo.permission.Main;
import org.yaml.snakeyaml.Yaml;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Admin implements SetUserPerms{
    public static List<String> permsAdmin = new ArrayList<String>();
    private final File file = new File("./src/main/resources/permsADMIN.yml");
    private final Yaml yaml = new Yaml();

    @Override
    public Boolean CheckList(String user){
        return permsAdmin.contains(user);
    }

    public void ImportADMINYml(){
        try {
            if(file.exists() && !file.isDirectory()){
                Main.getLogRecord().setINFO("permsADMIN import success");
                InputStream inputStream = Files.newInputStream(file.toPath());
                permsAdmin = yaml.load(inputStream);
            }else{
                permsAdmin.add("Admin");
                PrintWriter printWriter = new PrintWriter(
                        new File("./src/main/resources/permsADMIN.yml"));
                yaml.dump(permsAdmin, printWriter);
                Main.getLogRecord().setINFO("permsADMIN Establish success");
            }
        }catch (Exception e){
            e.printStackTrace();
            Main.getLogRecord().setError("ERROR : " + e);
        }
    }

    public void UploadADMINYml(){
        Main.getLogRecord().setINFO("permsADMIN Upload success");
        try {
            PrintWriter printWriter = new PrintWriter(
                    new File("./src/main/resources/permsADMIN.yml"));
            yaml.dump(permsAdmin, printWriter);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Main.getLogRecord().setError("ERROR : " + e);
        }
    }

}
