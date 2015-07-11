package com.epsi.forms;

import com.epsi.entities.Visitor;
import com.epsi.entities.VisitorDAO;
import com.epsi.managers.Connection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
    private JTextField passwordText;
    private JTextField passwordCheckText;

    public SubscribeForm() {
        setContentPane(root);

        pack();
        setTitle("Inscription");
        setResizable(false);

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        subscribeButton.addActionListener(this);
    }

    /**
     * Ajoute le visiteur en base de données
     * @param e
     */
    public void actionPerformed(ActionEvent e) {
        if (this.areFieldsValid()) {
            Visitor visitor = new Visitor();

            byte[] hash = new byte[]{};

            try {
                MessageDigest md = MessageDigest.getInstance("SHA-256");

                hash = md.digest(this.passwordText.getText().getBytes());

            } catch (NoSuchAlgorithmException e1) {
                e1.printStackTrace();
            }

            visitor.setLogin(usernameText.getText());
            visitor.setPassword(new String(hash));
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

    /**
     * Vérifie la validité des champs du formulaire
     * @return boolean
     */
    private boolean areFieldsValid() {
        boolean valid = true;
        Color base = Color.black;
        Color error = Color.RED;

        usernameLabel.setForeground(base);
        zipCodeLabel.setForeground(base);
        passwordLabel.setForeground(base);
        passwordCheckLabel.setForeground(base);

        if (usernameText.getText().length() == 0) {
            usernameLabel.setForeground(error);
            valid = false;
        }

        if (!zipCodeText.getText().matches("^\\d{5}$")) {
            zipCodeLabel.setForeground(error);
            valid = false;
        }

        if (passwordText.getText().length() == 0) {
            passwordLabel.setForeground(error);
            valid = false;
        }
        else if (!passwordText.getText().equals(passwordCheckText.getText())) {
            passwordCheckLabel.setForeground(error);
            valid = false;
        }

        return valid;
    }
}
