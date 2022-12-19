package com.chenyo.permission;


import com.chenyo.permission.perms.UserPerms;
import com.chenyo.permission.terminal.Terminal;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.logging.Logger;


public class LoginFrame extends JFrame{
    public final JTextField textField;
    public final JPasswordField passwordField;
    public final JButton btn;
    public String user;
    private String password;

    private static Terminal terminal;
    private static UserPerms userPerms;
    public static UserPerms getUserPerms(){
        return userPerms;
    }
    public static Terminal getTerminal(){
        return terminal;
    }

    public LoginFrame(){
        super("Login");
        super.setLayout(null);

        terminal = new Terminal();
        textField = new JTextField(20);
        textField.setBounds(50,50,120,30);
        add(textField);

        passwordField = new JPasswordField();
        passwordField.setBounds(50,90,120,30);
        passwordField.addKeyListener(new LoginListener());
        add(passwordField);

        btn = new JButton("Login");
        btn.setBounds(50,120,100,30);
        btn.addActionListener(new ButtonHandler());
        add(btn);
    }

    public void startFrame(){
        setSize(300,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private Boolean CompareID(String user){
        return Main.getUserList().NameList.containsKey(user);
    }

    private void StartTerminal(){
        userPerms = new UserPerms();
        getUserPerms().setUserPermsList();
        getUserPerms().getPermsYml();
        terminal.start();
        terminal.AppendResult("......Terminal start......");
    }

    private class ButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            try{
                user = textField.getText();
                password = String.valueOf(passwordField.getPassword());
                if(CompareID(user) & Main.getUserList().ComparePassword(password)){
                    Logger logger = Logger.getLogger(user + "_LogRecord");
                    Main.getLogRecord().setLogger(logger);
                    Main.getLogRecord().LogRecorder();
                    Main.getLogRecord().setINFO(user + " : Login Success");

                    StartTerminal();
                    dispose();
                }else if(!CompareID(user)){
                    Main.getLogRecord().setWarning(user + " : Login Failed" + " -> no such ID");
                    JOptionPane.showMessageDialog(new JFrame(), "wrong ID", "Wrong",
                            JOptionPane.ERROR_MESSAGE);
                }else if(CompareID(user) & !Main.getUserList().ComparePassword(password)){
                    Main.getLogRecord().setWarning(user + " : Login Failed" + " -> wrong password");
                    JOptionPane.showMessageDialog(new JFrame(), "Wrong Password",
                            "WRONG", JOptionPane.ERROR_MESSAGE);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public class LoginListener extends KeyAdapter{
        public void keyPressed(KeyEvent keyEvent){
            if(keyEvent.getKeyCode() == KeyEvent.VK_ENTER){
                try{
                    user = textField.getText();
                    password = String.valueOf(passwordField.getPassword());
                    if(CompareID(user) & Main.getUserList().ComparePassword(password)){
                        Logger logger = Logger.getLogger(user + "_LogRecord");
                        Main.getLogRecord().setLogger(logger);
                        Main.getLogRecord().LogRecorder();

                        Main.getLogRecord().setINFO(user + " : Login Success");
                        StartTerminal();
                        dispose();
                    }else if(!CompareID(user)){
                        Main.getLogRecord().setWarning(user + " : Login Failed" + " -> no such ID");
                        JOptionPane.showMessageDialog(new JFrame(), "wrong ID", "Wrong",
                                JOptionPane.ERROR_MESSAGE);
                    }else if(CompareID(user) & !Main.getUserList().ComparePassword(password)){
                        Main.getLogRecord().setWarning(user + " : Login Failed" + " -> wrong password");
                        JOptionPane.showMessageDialog(new JFrame(), "Wrong Password",
                                "WRONG", JOptionPane.ERROR_MESSAGE);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    Main.getLogRecord().setError("Error : " + e);
                }
            }
        }
    }

}
