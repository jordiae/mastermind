package com.mastermind.presentacio;

import javax.swing.*;

public class InitScreen {
    private JButton logInButton;
    private JButton registerButton;
    private JPanel initView;

    public static void main(String[] args) {
        JFrame frame = new JFrame("InitScreen");
        frame.setContentPane(new InitScreen().initView);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
