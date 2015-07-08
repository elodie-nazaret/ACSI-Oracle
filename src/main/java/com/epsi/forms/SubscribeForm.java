package com.epsi.forms;

import com.epsi.entities.Visitor;
import com.epsi.entities.VisitorDAO;
import com.epsi.managers.Connection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Date;


public class SubscribeForm extends JFrame implements ActionListener {

    private JPanel root;
    private JPanel header;
    private JPanel body;
    private JPanel footer;
    private JLabel titleLabel;
    private JPanel usernamePanel;
    private JPanel passwordPanel;
    private JPanel passwordCheckPanel;
    private JPanel zipCodePanel;
    private JTextField usernameText;
    private JTextField zipCodeText;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JLabel passwordCheckLabel;
    private JLabel zipCodeLabel;
    private JButton subscribeButton;
    private JPasswordField passwordText;
    private JPasswordField passwordCheckText;

    public SubscribeForm() {
        setContentPane(root);

        pack();
        setTitle("Inscription");
        setResizable(false);

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        subscribeButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (this.areFieldsValid()) {
            Visitor visitor = new Visitor();

            visitor.setLogin(usernameText.getText());
            visitor.setPassword(new String(passwordText.getPassword()));
            visitor.setPostalCode(zipCodeText.getText());
            visitor.setSubscribeDate(new Date());

            VisitorDAO visitorDAO = new VisitorDAO();
            visitorDAO.addVisitor(visitor);

            Connection connection = Connection.getInstance();
            connection.setConnectedPeople(visitor);

            new HomeForm();
            this.dispose();
        }
    }


    private boolean areFieldsValid() {
        boolean valid = true;

        if (usernameText.getText().length() == 0) {
            usernameLabel.setForeground(Color.RED);
            valid = false;
        }

        if (!zipCodeText.getText().matches("^\\d{5}$")) {
            zipCodeLabel.setForeground(Color.RED);
            valid = false;
        }

        if (passwordText.getPassword().length == 0) {
            passwordLabel.setForeground(Color.RED);
            valid = false;
        }
        else if (!Arrays.equals(passwordText.getPassword(), passwordCheckText.getPassword())) {
            passwordCheckLabel.setForeground(Color.RED);
            valid = false;
        }

        return valid;
    }
}
