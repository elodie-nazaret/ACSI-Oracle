package com.epsi.forms;

import com.epsi.entities.ArticleDAO;
import com.epsi.managers.Connection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HomeForm extends JFrame implements ActionListener {
    private JPanel  root;
    private JButton newArticleButton;

    public HomeForm() {
        setContentPane(root);
        pack();
        setResizable(false);
        setVisible(true);

        newArticleButton.addActionListener(this);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                Connection.getInstance().endTour();
                dispose();
            }
        });

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.newArticleButton) {
            ArticleDAO articleDAO = new ArticleDAO();
            new ArticleForm(articleDAO.getArticleById(9));
        }
    }
}
