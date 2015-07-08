package com.epsi.forms;

import com.epsi.entities.Visitor;
import com.epsi.entities.WatchDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class StatsVisitorForm extends JFrame implements ActionListener {

    private JPanel  root;
    private JPanel  head;
    private JPanel  body;
    private JPanel  footer;
    private JButton buttonBack;
    private JPanel  countWatchTimeArticle;
    private JPanel  averageWatchTimeArticle;
    private JLabel titleLabel;

    private Visitor visitor;

    public StatsVisitorForm(Visitor visitor) {

        this.visitor = visitor;

        setContentPane(root);
        GridLayout gridLayout = new GridLayout(0, 1);
        this.countWatchTimeArticle.setLayout(gridLayout);
        this.averageWatchTimeArticle.setLayout(gridLayout);
        this.displayStats();

        setResizable(false);
        setTitle("Statistiques visiteur");

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
    }

    public void displayStatsCountWatchTime() {
        WatchDAO                 watchDAO = new WatchDAO();
        java.util.List<Object[]> results = watchDAO.getCountWatchTimeForAVisitor(this.visitor);

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
        jLabel.setText("Nombre de fois que j'ai consulté chaque article");

        this.countWatchTimeArticle.add(jLabel);
        this.countWatchTimeArticle.add(jScrollPane);
    }

    public void displayStatsAverageWatchTime() {
        WatchDAO                 watchDAO = new WatchDAO();
        java.util.List<Object[]> results = watchDAO.getAverageWatchTimeForAVisitor(this.visitor);

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
        jLabel.setText("Temps moyen que j'ai passé sur chaque article");

        this.averageWatchTimeArticle.add(jLabel);
        this.averageWatchTimeArticle.add(jScrollPane);
    }
}
