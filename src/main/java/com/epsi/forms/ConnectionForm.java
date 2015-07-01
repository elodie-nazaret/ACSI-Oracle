package com.epsi.forms;

import javax.swing.*;

public class ConnectionForm extends JFrame {
    private JPanel root;
    private JPanel head;
    private JPanel body;
    private JPanel footer;
    private JLabel titleLabel;
    private JTextField usernameText;
    private JTextField passwordText;
    private JButton createAccountButton;
    private JButton connectButton;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JPanel bodyTop;
    private JPanel bodyBottom;

    public ConnectionForm() {
        setContentPane(root);
        pack();
        setTitle("Connexion");
        setResizable(false);
        setVisible(true);
    }
}
