package com.epsi.forms;

import com.epsi.entities.*;
import com.epsi.managers.Connection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
            /**
             * Le visiteur n'a pas accès à certaines fonctionnalités
             */
            this.addArticleButton.setVisible(false);
            this.refreshButton.setVisible(false);
        }

        this.updateArticles();

        this.displayStats();
        this.statisticsButton.addActionListener(this);
        this.addArticleButton.addActionListener(this);
        this.refreshButton.addActionListener(this);

        setTitle("Page d'accueil");
        pack();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.statisticsButton) {
            /**
             * Affiche la page de statistiques correspondant au type d'utilisateur connecté
             */
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

    /**
     * Affiche les statistiques sur le nombre de personnes inscrites et le nombre de nouveaux inscrits
     */
    public void displayStats() {
        VisitorDAO visitorDAO = new VisitorDAO();
        Object countAllVisitors = visitorDAO.getCountAllVisitors();
        Object countNewSubscribers = visitorDAO.getCountNewSubscribersForMonth(this.simpleDateFormat.format(new Date()));

        this.totalSubscribersText.setText(this.totalSubscribersText.getText() + countAllVisitors.toString());
        this.newSubscribersText.setText(this.newSubscribersText.getText() + countNewSubscribers.toString());
    }

    /**
     * Met à jour la liste des articles
     */
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
            JPanel jPanel = (new HomeArticle(article)).getAll();
            this.gridPanel.add(jPanel);
        }

        this.revalidate();
        this.repaint();
    }
}
