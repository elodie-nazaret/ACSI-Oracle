package com.epsi.forms;

import com.epsi.entities.People;
import com.epsi.entities.PeopleDAO;
import com.epsi.managers.Connection;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ConnectionForm extends JFrame implements ActionListener {
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
    private JLabel errorCredentialsLabel;

    public ConnectionForm() {
        setContentPane(root);
        pack();
        setTitle("Connexion");
        setResizable(false);

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        connectButton.addActionListener(this);
        createAccountButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this.createAccountButton) {
            /**
             * Ouvre la form d'inscription
             */
            new SubscribeForm();
            this.dispose();

        } else if (e.getSource() == this.connectButton) {
            /**
             * Essaye de connecter l'utilisateur et affiche un message si le compte n'existe pas
             */
            PeopleDAO peopleDAO = new PeopleDAO();
            byte[] hash = new byte[]{};

            try {
                MessageDigest md = MessageDigest.getInstance("SHA-256");

                hash = md.digest(this.passwordText.getText().getBytes());

            } catch (NoSuchAlgorithmException e1) {
                e1.printStackTrace();
            }

            People people = peopleDAO.checkLoginAndPassword(this.usernameText.getText(), new String(hash));

            if (people != null) {
                Connection connection = Connection.getInstance();
                connection.setConnectedPeople(people);

                new HomeForm();
                this.dispose();
            } else {
                errorCredentialsLabel.setText("Mauvais identifiants");
            }
        }
    }
}
