package com.epsi.forms;

import com.epsi.entities.*;
import com.epsi.managers.Connection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class HomeForm extends JFrame implements ActionListener {
    private JPanel  root;
    private JPanel  gridPanel;
    private JPanel  bottomPanel;
    private JLabel  newSubscribersText;
    private JLabel  totalSubscribersText;
    private JButton addArticleButton;
    private JButton statisticsButton;
    private JPanel  statPanel;
    private JPanel  buttonPanel;

    private SimpleDateFormat simpleDateFormat;

    public HomeForm() {

        this.simpleDateFormat = new SimpleDateFormat("YYYY/MM");
        setContentPane(root);
        setResizable(false);

        GridLayout grid = new GridLayout(-1, 3, 5, 5);

        this.gridPanel.setLayout(grid);

        ArticleDAO articleDAO = new ArticleDAO();

        List<Article> articleList;

        if (Connection.getInstance().getConnectedPeople() instanceof Admin) {
            articleList = articleDAO.getAllArticles();
        } else {
            articleList = articleDAO.getAllArticlesVisible();
        }

        for (Article article : articleList) {
            JPanel jPanel = this.setArticleForm(article);
            this.gridPanel.add(jPanel);
        }

        if (Connection.getInstance().getConnectedPeople() instanceof Visitor) {
            this.addArticleButton.setVisible(false);
        }

        this.displayStats();
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
                new StatsVisitorForm((Visitor)Connection.getInstance().getConnectedPeople());
            }
        }
        else if (e.getSource() == this.addArticleButton) {
            new CreateUpdateArticleForm();
        }
    }

    public void displayStats() {
        VisitorDAO visitorDAO = new VisitorDAO();
        Object countAllVisitors = visitorDAO.getCountAllVisitors();
        Object countNewSubscribers = visitorDAO.getCountNewSubscribersForMonth(this.simpleDateFormat.format(new Date()));

        this.newSubscribersText.setText(this.newSubscribersText.getText() + countNewSubscribers.toString());
        this.totalSubscribersText.setText(this.totalSubscribersText.getText() + countAllVisitors.toString());
    }
}
