package com.epsi.forms;

import com.epsi.entities.People;
import com.epsi.entities.PeopleDAO;
import com.epsi.managers.Connection;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        setVisible(true);

        connectButton.addActionListener(this);
        createAccountButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this.createAccountButton) {
            new SubscribeForm();
            this.dispose();

        } else if (e.getSource() == this.connectButton) {
            PeopleDAO peopleDAO = new PeopleDAO();
            People people = peopleDAO.checkLoginAndPassword(this.usernameText.getText(), this.passwordText.getText());

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
