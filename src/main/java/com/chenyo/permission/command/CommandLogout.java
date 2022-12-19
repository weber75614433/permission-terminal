package com.chenyo.permission.command;

import com.chenyo.permission.LoginFrame;
import com.chenyo.permission.Main;
import javax.swing.*;

public class CommandLogout implements Runnable, PermsVerify{

    @Override
    public void ConfirmPerms() {
        run();
    }

    @Override
    public void run() {

        JOptionPane.showMessageDialog(new JFrame(), "Logout success", "Logout",
                JOptionPane.INFORMATION_MESSAGE);
        try {
            Main.getLogRecord().setINFO(Main.getLoginFrame().user + " : " + "Logout");
            Main.getUserList().uploadYml();
            LoginFrame.getUserPerms().uploadStatus();
            LoginFrame.getTerminal().Dispose();
            Main.getLoginFrame().textField.setText(null);
            Main.getLoginFrame().passwordField.setText(null);
            LoginFrame.getTerminal().textArea.setText(null);
            Main.getLoginFrame().startFrame();


        } catch (Exception e) {
            e.printStackTrace();
            Main.getLogRecord().setError("Error : " + e);
        }


    }
}
