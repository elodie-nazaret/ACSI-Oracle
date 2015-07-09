package com.epsi.forms;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class StatForm extends JFrame {
    protected void addStatTable(JPanel targetPanel, Object[] columns, List<Object[]> values, String title) {
        JTable jTable = new JTable();
        DefaultTableModel tableModel = new DefaultTableModel(0, 0);
        jTable.setModel(tableModel);
        tableModel.setColumnIdentifiers(columns);

        for (Object[] value : values) {
            tableModel.addRow(value);
        }

        Dimension dimension = jTable.getPreferredSize();
        JScrollPane jScrollPane = new JScrollPane(jTable);
        jScrollPane.setPreferredSize(new Dimension(dimension.width, jTable.getRowHeight() * values.size() + 2));

        JLabel jLabel = new JLabel();
        jLabel.setText(title);

        targetPanel.add(jLabel);
        targetPanel.add(jScrollPane);
    }
}
