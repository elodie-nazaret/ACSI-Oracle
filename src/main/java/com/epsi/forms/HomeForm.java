package com.epsi.forms;

import com.epsi.entities.Admin;
import com.epsi.entities.Article;
import com.epsi.entities.ArticleDAO;
import com.epsi.managers.Connection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class HomeForm extends JFrame implements ActionListener {
    private JPanel root;
    private JPanel gridPanel;
    private JPanel bottomPanel;
    private JLabel newSubscribersText;
    private JLabel totalSubscribersText;
    private JButton addArticleButton;
    private JButton statisticsButton;
    private JPanel statPanel;
    private JPanel buttonPanel;


    public HomeForm() {
        setContentPane(root);
        setResizable(false);

        GridLayout grid = new GridLayout(-1, 3, 5, 5);

        this.gridPanel.setLayout(grid);

        ArticleDAO articleDAO = new ArticleDAO();

        for (Article article : articleDAO.getAllArticles()) {
            JPanel jPanel = this.setArticleForm(article);
            this.gridPanel.add(jPanel);
        }

        this.statisticsButton.addActionListener(this);
        this.addArticleButton.addActionListener(this);
        pack();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }


    private JPanel setArticleForm(Article article) {
        HomeArticle homeArticle = new HomeArticle(article);
        return homeArticle.getAll();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.statisticsButton) {
            if (Connection.getInstance().getConnectedPeople() instanceof Admin) {
                new StatsAdminForm();
            }
            else {
                new StatsVisitorForm();
            }
        }
        else if (e.getSource() == this.addArticleButton) {
            new CreateUpdateArticleForm();
        }
    }
}
