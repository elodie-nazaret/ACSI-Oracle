package com.epsi.forms;

import com.epsi.entities.WatchDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class StatsAdminForm extends JFrame implements ActionListener {

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

        JTable jTable = new JTable();
        DefaultTableModel tableModel = new DefaultTableModel(0, 0);
        jTable.setModel(tableModel);

        tableModel.setColumnIdentifiers(new Object[]{"Article", "Mois", "Nombre de consultations"});

        for (Object[] result : results) {
            tableModel.addRow(result);
        }

        Dimension dimension = jTable.getPreferredSize();
        JScrollPane jScrollPane = new JScrollPane(jTable);
        jScrollPane.setPreferredSize(new Dimension(dimension.width, jTable.getRowHeight() * results.size() + 1));
        JLabel jLabel = new JLabel();
        jLabel.setText("Nombre moyen de consultation au fil du temps pour tous les articles");

        this.countWatchTimeArticlesPanel.add(jLabel);
        this.countWatchTimeArticlesPanel.add(jScrollPane);
    }

    public void displayStatsAverageWatchTime() {
        WatchDAO watchDAO = new WatchDAO();
        List<Object[]> results = watchDAO.getAverageWatchTimeForAllArticles();

        JTable jTable = new JTable();
        DefaultTableModel tableModel = new DefaultTableModel(0, 0);
        jTable.setModel(tableModel);

        tableModel.setColumnIdentifiers(new Object[]{"Article", "Mois", "Temps moyen de consultation"});

        for (Object[] result : results) {
            tableModel.addRow(result);
        }

        Dimension dimension = jTable.getPreferredSize();
        JScrollPane jScrollPane = new JScrollPane(jTable);
        jScrollPane.setPreferredSize(new Dimension(dimension.width, jTable.getRowHeight() * results.size() + 1));
        JLabel jLabel = new JLabel();
        jLabel.setText("Temps moyen de consultation au fil du temps pour tous les articles");

        this.averageWatchTimeArticlesPanel.add(jLabel);
        this.averageWatchTimeArticlesPanel.add(jScrollPane);
    }

    public void displayStatsTopCp() {
        WatchDAO watchDAO = new WatchDAO();
        List<Object[]> results = watchDAO.getTopCpForAllArticles();

        JTable jTable = new JTable();
        DefaultTableModel tableModel = new DefaultTableModel(0, 0);
        jTable.setModel(tableModel);

        tableModel.setColumnIdentifiers(new Object[]{"Code postal", "Mois", "Nombre de consultations"});

        for (Object[] result : results) {
            tableModel.addRow(result);
        }

        Dimension dimension = jTable.getPreferredSize();
        JScrollPane jScrollPane = new JScrollPane(jTable);
        jScrollPane.setPreferredSize(new Dimension(dimension.width, jTable.getRowHeight() * results.size() + 1));
        JLabel jLabel = new JLabel();
        jLabel.setText("Top des codes postaux ayant le plus consulté d'articles");

        this.topCpArticlesPanel.add(jLabel);
        this.topCpArticlesPanel.add(jScrollPane);
    }

    public void displayStatsTopArticlesCountWatchTime() {
        WatchDAO watchDAO = new WatchDAO();
        List<Object[]> results = watchDAO.getTopArticlesCountWatchTime();

        JTable jTable = new JTable();
        DefaultTableModel tableModel = new DefaultTableModel(0, 0);
        jTable.setModel(tableModel);

        tableModel.setColumnIdentifiers(new Object[]{"Article", "Nombre de consultations"});

        for (Object[] result : results) {
            tableModel.addRow(result);
        }

        Dimension dimension = jTable.getPreferredSize();
        JScrollPane jScrollPane = new JScrollPane(jTable);
        jScrollPane.setPreferredSize(new Dimension(dimension.width, jTable.getRowHeight() * results.size() + 1));
        JLabel jLabel = new JLabel();
        jLabel.setText("Top des articles ayant été le plus consulté");

        this.topArticlesCountWatchTimePanel.add(jLabel);
        this.topArticlesCountWatchTimePanel.add(jScrollPane);
    }

    public void displayStatsTopArticlesAverageWatchTime() {
        WatchDAO watchDAO = new WatchDAO();
        List<Object[]> results = watchDAO.getTopArticlesAverageWatchTime();

        JTable jTable = new JTable();
        DefaultTableModel tableModel = new DefaultTableModel(0, 0);
        jTable.setModel(tableModel);

        tableModel.setColumnIdentifiers(new Object[]{"Article", "Temps moyen de consultation"});

        for (Object[] result : results) {
            tableModel.addRow(result);
        }

        Dimension dimension = jTable.getPreferredSize();
        JScrollPane jScrollPane = new JScrollPane(jTable);
        jScrollPane.setPreferredSize(new Dimension(dimension.width, jTable.getRowHeight() * results.size() + 1));
        JLabel jLabel = new JLabel();
        jLabel.setText("Top des articles que l'on regarde le plus longtemps");

        this.topArticlesAverageWatchTimePanel.add(jLabel);
        this.topArticlesAverageWatchTimePanel.add(jScrollPane);
    }

}
