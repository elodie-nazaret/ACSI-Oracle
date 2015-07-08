package com.epsi.forms;

import com.epsi.entities.WatchDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class StatsAdminForm extends StatForm implements ActionListener {

    private JPanel  root;
    private JPanel  head;
    private JPanel  averageWatchTimeArticlesPanel;
    private JPanel  countWatchTimeArticlesPanel;
    private JPanel  topCpArticlesPanel;
    private JPanel  topArticlesCountWatchTimePanel;
    private JPanel  topArticlesAverageWatchTimePanel;
    private JPanel  footer;
    private JButton buttonBack;
    private JPanel  body;
    private JLabel titleLabel;

    public StatsAdminForm() {

        setContentPane(root);
        GridLayout gridLayout = new GridLayout(0, 1);
        this.averageWatchTimeArticlesPanel.setLayout(gridLayout);
        this.countWatchTimeArticlesPanel.setLayout(gridLayout);
        this.topCpArticlesPanel.setLayout(gridLayout);
        this.topArticlesCountWatchTimePanel.setLayout(gridLayout);
        this.topArticlesAverageWatchTimePanel.setLayout(gridLayout);
        this.displayStats();

        setResizable(false);
        setTitle("Statistiques administrateur");

        this.buttonBack.addActionListener(this);
        pack();
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.buttonBack) {
            this.dispose();
        }
    }

    public void displayStats() {

        this.displayStatsCountWatchTime();
        this.displayStatsAverageWatchTime();
        this.displayStatsTopCp();
        this.displayStatsTopArticlesCountWatchTime();
        this.displayStatsTopArticlesAverageWatchTime();
    }

    public void displayStatsCountWatchTime() {
        WatchDAO watchDAO = new WatchDAO();
        List<Object[]> results = watchDAO.getCountWatchTimeForAllArticles();

        this.addStatTable(this.countWatchTimeArticlesPanel, new Object[]{"Article", "Mois", "Nombre de consultations"}, results, "Nombre moyen de consultation au fil du temps pour tous les articles");
    }

    public void displayStatsAverageWatchTime() {
        WatchDAO watchDAO = new WatchDAO();
        List<Object[]> results = watchDAO.getAverageWatchTimeForAllArticles();

        this.addStatTable(this.averageWatchTimeArticlesPanel, new Object[]{"Article", "Mois", "Temps moyen de consultations"}, results, "Temps moyen de consultation au fil du temps pour tous les articles");
    }

    public void displayStatsTopCp() {
        WatchDAO watchDAO = new WatchDAO();
        List<Object[]> results = watchDAO.getTopCpForAllArticles();

        this.addStatTable(this.topCpArticlesPanel, new Object[]{"Code postal", "Nombre de consultations"}, results, "Top des codes postaux ayant le plus consulté d'articles");
    }

    public void displayStatsTopArticlesCountWatchTime() {
        WatchDAO watchDAO = new WatchDAO();
        List<Object[]> results = watchDAO.getTopArticlesCountWatchTime();

        this.addStatTable(this.topArticlesCountWatchTimePanel, new Object[]{"Article", "Nombre de consultations"}, results, "Top des articles ayant été le plus consulté");
    }

    public void displayStatsTopArticlesAverageWatchTime() {
        WatchDAO watchDAO = new WatchDAO();
        List<Object[]> results = watchDAO.getTopArticlesAverageWatchTime();

        this.addStatTable(this.topArticlesAverageWatchTimePanel, new Object[]{"Article", "Temps moyen de consultations"}, results, "Top des articles que l'on regarde le plus longtemps");
    }

}
