package com.epsi.forms;

import com.epsi.entities.Article;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class HomeArticle implements ActionListener {
    private JPanel  root;
    private JLabel  imageLabel;
    private JLabel  referenceText;
    private JLabel  priceText;
    private JLabel  nameText;
    private JButton detailButton;
    private JPanel infosPanel;

    private Article article;

    public HomeArticle(Article article) {
        this.article = article;

        if (!article.isVisible()) {
            this.root.setBackground(Color.LIGHT_GRAY);
            this.infosPanel.setBackground(Color.LIGHT_GRAY);
        }

        if (this.article.getImage() != null) {
            this.imageLabel.setIcon(new ImageIcon(this.article.getImage()));
        }

        this.nameText.setText(this.article.getDesignation());
        this.referenceText.setText(this.article.getReference());
        this.priceText.setText(String.valueOf(this.article.getPrice()) + "€");

        this.detailButton.addActionListener(this);
    }

    public JPanel getAll() {
        return root;
    }

    public void actionPerformed(ActionEvent e) {
        new ArticleForm(this.article);
    }
}
