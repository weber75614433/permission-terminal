package com.chenyo.permission.terminal;


import com.chenyo.permission.CommandProcessing;
import com.chenyo.permission.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Terminal{
    public final JTextField textField;
    public final JTextArea textArea ;
    private final JFrame frame;
    public String command;
    public String perms;

    public Terminal(){
        frame = new JFrame("Terminal");
        textField = new JTextField(20);
        textField.addKeyListener(new CommandListener());
        textArea = new JTextArea();

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(textArea, BorderLayout.CENTER);
        frame.getContentPane().add(textField, BorderLayout.NORTH);

        frame.setBounds(0,0,600,600);
    }

    public void start(){
        frame.setVisible(true);
    }

    public void Dispose() throws Exception {
        frame.dispose();
    }

    public  String getcommand(){
        return command;
    }

    public  void AppendResult(String result){
        textArea.append(result + "\n");
    }

    public class CommandListener extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_ENTER){
                command = textField.getText();
                getcommand();
                AppendResult("> " + command);
                new CommandProcessing().run();
                AppendResult(Main.getCommandProcessing().getResult());
                textField.setText(null);
            }
        }
    }

}
