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

public class Advanced implements SetUserPerms{

    public static  List<String> permsAdvanced = new ArrayList<String>();
    private final File file = new File("./src/main/resources/permsADVANCED.yml");
    private final Yaml yaml = new Yaml();

    @Override
    public void Extends(){
        permsAdvanced.addAll(UserPerms.getAdmin().permsAdmin);
    }

    @Override
    public Boolean CheckList(String user){
        return permsAdvanced.contains(user);
    }

    public void ImportADVANCEDYml(){
        try {
            if(file.exists() && !file.isDirectory()){
                Main.getLogRecord().setINFO("permsADVANCED import success");
                InputStream inputStream = Files.newInputStream(file.toPath());
                permsAdvanced = yaml.load(inputStream);
            }else{
                permsAdvanced.add("Danny");
                PrintWriter printWriter = new PrintWriter(
                        new File("./src/main/resources/permsADVANCED.yml"));
                yaml.dump(permsAdvanced, printWriter);
                Main.getLogRecord().setINFO("permsADVANCED Establish success");
            }
        }catch (Exception e){
            e.printStackTrace();
            Main.getLogRecord().setError("ERROR : " + e);
        }
    }

    public void UploadADVANCEDYml(){
        Main.getLogRecord().setINFO("permsADVANCED Upload success");
        try {
            PrintWriter printWriter = new PrintWriter(
                    new File("./src/main/resources/permsADVANCED.yml"));
            yaml.dump(permsAdvanced, printWriter);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Main.getLogRecord().setError("ERROR : " + e);
        }
    }

}
