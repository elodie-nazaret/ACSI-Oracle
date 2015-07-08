package com.epsi.forms;

import com.epsi.entities.Article;
import com.epsi.entities.ArticleDAO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class CreateUpdateArticleForm extends JFrame implements ActionListener {
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
    private JButton cancelButton;

    private Article article;

    public CreateUpdateArticleForm() {
        this.init();

        this.article = null;

        setTitle("Nouvel article");
    }

    public CreateUpdateArticleForm(Article article) {
        this.init();

        this.article = article;
        this.referenceText.setText(this.article.getReference());
        this.designationText.setText(this.article.getDesignation());
        this.descriptionText.setText(this.article.getDescription());
        this.priceText.setText(String.valueOf(this.article.getPrice()));

        setTitle("Editer " + this.article.getDesignation());
    }

    private void init() {
        setContentPane(root);
        pack();
        setResizable(false);
        setVisible(true);

        validateButton.addActionListener(this);
        cancelButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {

        ArticleDAO articleDAO = new ArticleDAO();

        if (e.getSource() == this.cancelButton) {
            this.dispose();

        } else if (e.getSource() == this.validateButton) {
            if (this.article == null) {
                Article article = new Article();

                article.setDesignation(this.designationText.getText());
                article.setDescription(this.descriptionText.getText());
                article.setReference(this.referenceText.getText());
                article.setPrice(Float.valueOf(this.priceText.getText()));
                article.setIsVisible(true);
                article.setCreatedAt(new Date());
                article.setUpdatedAt(new Date());

                articleDAO.addArticle(article);
                new ArticleForm(article);
                this.dispose();

            } else {
                this.article.setReference(this.referenceText.getText());
                this.article.setDesignation(this.designationText.getText());
                this.article.setDescription(this.descriptionText.getText());
                this.article.setPrice(Float.valueOf(this.priceText.getText()));
                this.article.setUpdatedAt(new Date());

                articleDAO.updateArticle(this.article);
                this.dispose();
            }

        }
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
