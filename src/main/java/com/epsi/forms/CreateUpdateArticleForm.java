package com.epsi.forms;

import javax.swing.*;

public class CreateUpdateArticleForm extends JFrame{
    private JPanel root;
    private JPanel head;
    private JPanel body;
    private JPanel footer;
    private JLabel titleLabel;
    private JPanel imagePanel;
    private JPanel detailsPanel;
    private JButton validateButton;
    private JPanel referencePanel;
    private JPanel designationPanel;
    private JPanel pricePanel;
    private JPanel descriptionPanel;
    private JTextField referenceText;
    private JLabel referenceLabel;
    private JTextField designationText;
    private JTextField priceText;
    private JLabel designationLabel;
    private JLabel priceLabel;
    private JTextField descriptionText;
    private JLabel descriptionLabel;

    public CreateUpdateArticleForm() {
        setContentPane(root);
        pack();
        setTitle("Connexion");
        setResizable(false);
        setVisible(true);
    }
}
