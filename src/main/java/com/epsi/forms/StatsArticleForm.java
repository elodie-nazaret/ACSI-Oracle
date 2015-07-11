package com.epsi.forms;

import com.epsi.entities.Article;
import com.epsi.entities.WatchDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class StatsArticleForm extends StatForm implements ActionListener {

    private Article article;

    private JPanel root;
    private JPanel head;
    private JLabel titleLabel;
    private JPanel body;
    private JPanel averageWatchTimePanel;
    private JPanel countWatchTimePanel;
    private JPanel topVisitorPanel;
    private JPanel topCPPanel;
    private JPanel footer;
    private JButton buttonBack;

    private SimpleDateFormat simpleDateFormat;

    public StatsArticleForm(Article article) {
        this.article = article;
        this.simpleDateFormat = new SimpleDateFormat("YYYY/MM");

        setContentPane(root);
        GridLayout gridLayout = new GridLayout(0, 1);
        this.averageWatchTimePanel.setLayout(gridLayout);
        this.countWatchTimePanel.setLayout(gridLayout);
        this.topVisitorPanel.setLayout(gridLayout);
        this.topCPPanel.setLayout(gridLayout);
        this.displayStats();
        this.titleLabel.setText("Statistiques pour " + this.article.getDesignation());

        setResizable(false);
        setTitle("Statistiques pour " + this.article.getDesignation());

        this.buttonBack.addActionListener(this);
        pack();
        setVisible(true);
    }

    /**
     * Affiche les statistiques
     */
    public void displayStats() {
        displayStatsAverageWatchTime();
        displayStatsCountWatchTime();
        displayTopVisitorForArticle();
        displayTopCpForArticle();
    }

    /**
     * Récupère les statistiques sur le temps de consultation moyen de l'article
     * @return HashMap
     */
    public HashMap<String,Float> getStatsAverageWatchTimeForArticle() {

        WatchDAO watchDAO = new WatchDAO();
        java.util.List<Object[]> resultAverageWatchTimeForArticle = watchDAO.getAverageWatchTimeForArticle(this.article);

        HashMap<String,Float> statsAverageWatchTimeForArticle = new HashMap<String, Float>();

        Calendar calendar = Calendar.getInstance();
        statsAverageWatchTimeForArticle.put(this.simpleDateFormat.format(calendar.getTime()), 0.0f);
        calendar.add(Calendar.MONTH, -1);
        statsAverageWatchTimeForArticle.put(this.simpleDateFormat.format(calendar.getTime()), 0.0f);
        calendar.add(Calendar.MONTH, -1);
        statsAverageWatchTimeForArticle.put(this.simpleDateFormat.format(calendar.getTime()), 0.0f);

        for (Object[] result : resultAverageWatchTimeForArticle) {
            statsAverageWatchTimeForArticle.put(result[0].toString(), Float.valueOf(result[1].toString()));
        }

        return statsAverageWatchTimeForArticle;
    }

    /**
     * Récupère les statistiques sur le nombre de consultations de l'article
     * @return HashMap
     */
    public HashMap<String,Integer> getStatsCountWatchTimeForArticle() {

        WatchDAO watchDAO = new WatchDAO();
        java.util.List<Object[]> resultCountWatchTimeForArticle = watchDAO.getCountWatchTimeForArticle(this.article);

        HashMap<String,Integer> statsCountWatchTimeForArticle = new HashMap<String, Integer>();

        Calendar calendar = Calendar.getInstance();
        statsCountWatchTimeForArticle.put(this.simpleDateFormat.format(calendar.getTime()), 0);
        calendar.add(Calendar.MONTH, -1);
        statsCountWatchTimeForArticle.put(this.simpleDateFormat.format(calendar.getTime()), 0);
        calendar.add(Calendar.MONTH, -1);
        statsCountWatchTimeForArticle.put(this.simpleDateFormat.format(calendar.getTime()), 0);

        for (Object[] result : resultCountWatchTimeForArticle) {
            statsCountWatchTimeForArticle.put(result[0].toString(), Integer.valueOf(result[1].toString()));
        }

        return statsCountWatchTimeForArticle;
    }

    /**
     * Affiche les statistiques sur le temps moyen de consultation de l'article
     */
    public void displayStatsAverageWatchTime() {
        HashMap<String,Float> stats = getStatsAverageWatchTimeForArticle();
        Map<String, Float> sortedStats = new TreeMap<String, Float>(stats);

        List<Object[]> results = new ArrayList<Object[]>();
        results.add(sortedStats.values().toArray());

        this.addStatTable(this.averageWatchTimePanel, sortedStats.keySet().toArray(), results, "Temps moyen de consultation au fil du temps");
    }

    /**
     * Affiche les statistiques sur le temps moyen de consultation de l'article
     */
    public void displayStatsCountWatchTime() {
        HashMap<String,Integer> stats = getStatsCountWatchTimeForArticle();
        Map<String, Integer> sortedStats = new TreeMap<String, Integer>(stats);

        List<Object[]> results = new ArrayList<Object[]>();
        results.add(sortedStats.values().toArray());

        this.addStatTable(this.countWatchTimePanel, sortedStats.keySet().toArray(), results, "Nombre moyen de consultation au fil du temps");
    }

    /**
     * Affiche le top 5 des visiteurs ayant le plus consulté l'article
     */
    public void displayTopVisitorForArticle() {
        WatchDAO watchDAO = new WatchDAO();
        List<Object[]> results = watchDAO.getTopVisitorForArticle(this.article);

        this.addStatTable(this.topVisitorPanel, new Object[]{"Visiteur", "Nombre de consultations"}, results, "Top des visiteurs ayant consulté l'article");
    }

    /**
     * Affiche le top 5 des code postaux ayant le plus consulté l'article
     */
    public void displayTopCpForArticle() {
        WatchDAO watchDAO = new WatchDAO();
        List<Object[]> results = watchDAO.getTopCpForArticle(this.article);

        this.addStatTable(this.topCPPanel, new Object[]{"Code postal", "Nombre de consultations"}, results, "Top des codes postaux ayant consulté l'article");
    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.buttonBack) {
            this.dispose();
        }
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
