package com.epsi.forms;

import com.epsi.entities.*;
import com.epsi.managers.Connection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    private JButton refreshButton;

    private SimpleDateFormat simpleDateFormat;

    public HomeForm() {

        this.simpleDateFormat = new SimpleDateFormat("YYYY/MM");
        setContentPane(root);
        setResizable(false);

        GridLayout grid = new GridLayout(-1, 2, 5, 5);

        this.gridPanel.setLayout(grid);

        if (Connection.getInstance().getConnectedPeople() instanceof Visitor) {
            this.addArticleButton.setVisible(false);
        }

        ArticleDAO articleDAO = new ArticleDAO();
        this.updateArticles();

        
        this.displayStats();
        this.statisticsButton.addActionListener(this);
        this.addArticleButton.addActionListener(this);
        this.refreshButton.addActionListener(this);

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
        else if (e.getSource() == this.refreshButton) {
            this.updateArticles();
        }
    }

    public void displayStats() {
        VisitorDAO visitorDAO = new VisitorDAO();
        Object countAllVisitors = visitorDAO.getCountAllVisitors();
        Object countNewSubscribers = visitorDAO.getCountNewSubscribersForMonth(this.simpleDateFormat.format(new Date()));

        this.newSubscribersText.setText(this.newSubscribersText.getText() + countNewSubscribers.toString());
        this.totalSubscribersText.setText(this.totalSubscribersText.getText() + countAllVisitors.toString());
    }

    public void updateArticles() {
        this.gridPanel.removeAll();

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

        this.revalidate();
        this.repaint();
    }
}
