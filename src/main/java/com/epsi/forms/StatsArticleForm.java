package com.epsi.forms;

import com.epsi.entities.Article;
import com.epsi.entities.WatchDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class StatsArticleForm extends JFrame implements ActionListener {

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

    public void displayStats() {

        displayStatsAverageWatchTime();
        displayStatsCountWatchTime();
        displayTopVisitorForArticle();
        displayTopCpForArticle();
    }

    public void displayStatsAverageWatchTime() {
        HashMap<String,Float> statsAverageWatchTimeForArticle = getStatsAverageWatchTimeForArticle();
        Map<String, Float> sortedStatsAverageWatchTimeForArticle = new TreeMap<String, Float>(statsAverageWatchTimeForArticle);

        JTable tableStatsAverageWatchTimeForArticle = new JTable();
        DefaultTableModel dtmStatsAverageWatchTimeForArticle = new DefaultTableModel(0, 0);
        tableStatsAverageWatchTimeForArticle.setModel(dtmStatsAverageWatchTimeForArticle);

        dtmStatsAverageWatchTimeForArticle.setColumnIdentifiers(sortedStatsAverageWatchTimeForArticle.keySet().toArray());
        dtmStatsAverageWatchTimeForArticle.addRow(sortedStatsAverageWatchTimeForArticle.values().toArray());

        Dimension dimension = tableStatsAverageWatchTimeForArticle.getPreferredSize();
        JScrollPane jScrollPane = new JScrollPane(tableStatsAverageWatchTimeForArticle);
        jScrollPane.setPreferredSize(new Dimension(dimension.width, tableStatsAverageWatchTimeForArticle.getRowHeight() * statsAverageWatchTimeForArticle.size() + 1));
        JLabel labelTableAverageWatchTime = new JLabel();
        labelTableAverageWatchTime.setText("Temps moyen de consultation au fil du temps");
        this.averageWatchTimePanel.add(labelTableAverageWatchTime);
        this.averageWatchTimePanel.add(jScrollPane);
    }

    public void displayStatsCountWatchTime() {
        HashMap<String,Integer> statsCountWatchTimeForArticle = getStatsCountWatchTimeForArticle();
        Map<String, Integer> sortedStatsCountWatchTimeForArticle = new TreeMap<String, Integer>(statsCountWatchTimeForArticle);

        JTable tableStatsCountWatchTimeForArticle = new JTable();
        DefaultTableModel dtmStatsCountWatchTimeForArticle = new DefaultTableModel(0, 0);
        tableStatsCountWatchTimeForArticle.setModel(dtmStatsCountWatchTimeForArticle);

        dtmStatsCountWatchTimeForArticle.setColumnIdentifiers(sortedStatsCountWatchTimeForArticle.keySet().toArray());
        dtmStatsCountWatchTimeForArticle.addRow(sortedStatsCountWatchTimeForArticle.values().toArray());

        Dimension dimension = tableStatsCountWatchTimeForArticle.getPreferredSize();
        JScrollPane jScrollPane = new JScrollPane(tableStatsCountWatchTimeForArticle);
        jScrollPane.setPreferredSize(new Dimension(dimension.width, tableStatsCountWatchTimeForArticle.getRowHeight() * statsCountWatchTimeForArticle.size() + 1));
        JLabel labelTableCountWatchTime = new JLabel();
        labelTableCountWatchTime.setText("Nombre moyen de consultation au fil du temps");
        this.countWatchTimePanel.add(labelTableCountWatchTime);
        this.countWatchTimePanel.add(jScrollPane);
    }

    public void displayTopVisitorForArticle() {
        HashMap<Integer,String> statsTopVisitorForArticle = getStatsTopVisitorForArticle();
        Map<Integer,String> sortedStatsTopVisitorForArticle = new TreeMap<Integer,String>(statsTopVisitorForArticle).descendingMap();

        JTable tableStatsTopVisitorForArticle = new JTable();
        DefaultTableModel dtmStatsTopVisitorForArticle = new DefaultTableModel(0, 0);
        tableStatsTopVisitorForArticle.setModel(dtmStatsTopVisitorForArticle);
        dtmStatsTopVisitorForArticle.setColumnIdentifiers(new Object[]{"Visiteur", "Nombre de consultations"});

        for (Map.Entry<Integer,String> stat: sortedStatsTopVisitorForArticle.entrySet()) {
            dtmStatsTopVisitorForArticle.addRow(new Object[]{stat.getValue(), stat.getKey()});
        }

        Dimension dimension = tableStatsTopVisitorForArticle.getPreferredSize();
        JScrollPane jScrollPane = new JScrollPane(tableStatsTopVisitorForArticle);
        jScrollPane.setPreferredSize(new Dimension(dimension.width, tableStatsTopVisitorForArticle.getRowHeight() * statsTopVisitorForArticle.size() + 1));
        JLabel labelTableTopVisitor = new JLabel();
        labelTableTopVisitor.setText("Top des visiteurs ayant consulté l'article");
        this.topVisitorPanel.add(labelTableTopVisitor);
        this.topVisitorPanel.add(jScrollPane);
    }

    public void displayTopCpForArticle() {
        HashMap<Integer,String> statsTopCpForArticle = getStatsTopCpForArticle();
        Map<Integer, String> sortedStatsTopCpForArticle = new TreeMap<Integer, String>(statsTopCpForArticle).descendingMap();

        JTable tableStatsTopCpForArticle = new JTable();
        DefaultTableModel dtmStatsTopCpForArticle = new DefaultTableModel(0, 0);
        tableStatsTopCpForArticle.setModel(dtmStatsTopCpForArticle);
        dtmStatsTopCpForArticle.setColumnIdentifiers(new Object[]{"Code postal", "Nombre de consultations"});

        for (Map.Entry<Integer, String> stat: sortedStatsTopCpForArticle.entrySet()) {
            dtmStatsTopCpForArticle.addRow(new Object[]{stat.getValue(), stat.getKey()});
        }

        Dimension dimension = tableStatsTopCpForArticle.getPreferredSize();
        JScrollPane jScrollPane = new JScrollPane(tableStatsTopCpForArticle);
        jScrollPane.setPreferredSize(new Dimension(dimension.width, tableStatsTopCpForArticle.getRowHeight() * statsTopCpForArticle.size() + 1));
        JLabel labelTableTopCp = new JLabel();
        labelTableTopCp.setText("Top des codes postaux ayant consulté l'article");
        this.topCPPanel.add(labelTableTopCp);
        this.topCPPanel.add(jScrollPane);
    }

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

    public HashMap<Integer,String> getStatsTopVisitorForArticle() {

        WatchDAO watchDAO = new WatchDAO();
        List<Object[]> resultTopVisitorForArticle = watchDAO.getTopVisitorForArticle(this.article);

        HashMap<Integer,String> statsTopVisitorForArticle = new HashMap<Integer, String>();

        for (Object[] result : resultTopVisitorForArticle) {
            statsTopVisitorForArticle.put(Integer.valueOf(result[1].toString()), result[0].toString());
        }

        return statsTopVisitorForArticle;
    }

    public HashMap<Integer,String> getStatsTopCpForArticle() {

        WatchDAO watchDAO = new WatchDAO();
        List<Object[]> resultTopCpForArticle = watchDAO.getTopCpForArticle(this.article);

        HashMap<Integer,String> statsTopCpForArticle = new HashMap<Integer,String>();

        for (Object[] result : resultTopCpForArticle) {
            statsTopCpForArticle.put(Integer.valueOf(result[1].toString()), result[0].toString());
        }

        return statsTopCpForArticle;
    }

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
