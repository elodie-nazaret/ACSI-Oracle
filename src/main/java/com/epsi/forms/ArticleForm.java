package com.epsi.forms;

import javax.swing.*;

/**
 * Created by axel on 30/06/2015.
 */
public class ArticleForm extends Form {
    private JPanel root;
    private JPanel header;
    private JLabel titleLabel;
    private JTextArea imageLabel;
    private JPanel body;
    private JPanel detailPanel;
    private JPanel referencePanel;
    private JPanel pricePanel;
    private JPanel descriptionPanel;
    private JLabel referenceLabel;
    private JLabel priceLabel;
    private JLabel referenceText;
    private JLabel priceText;
    private JLabel descriptionLabel;
    private JButton editButton;
    private JButton hideButton;
    private JButton statisticsButton;
    private JPanel buttonsPanel;

    public ArticleForm() {
        super();

        setContentPane(root);

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);

    }
}
