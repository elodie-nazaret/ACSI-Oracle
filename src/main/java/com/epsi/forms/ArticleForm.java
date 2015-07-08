package com.epsi.forms;

import com.epsi.entities.Article;
import com.epsi.entities.ArticleDAO;
import com.epsi.entities.Visitor;
import com.epsi.managers.Connection;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;
import java.util.List;

public class ArticleForm extends JFrame implements ActionListener {
    private JPanel root;
    private JPanel header;
    private JLabel titleLabel;
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
    private JLabel imageLabel;

    private Article article;
    private Watch watch;

    public ArticleForm() {
        setContentPane(root);
        pack();
        setResizable(false);
        setTitle("title");
        setVisible(true);
    }


    public ArticleForm(Article article) {
        this.article = article;

        this.referenceText.setText(this.article.getReference());
        this.priceText.setText(String.valueOf(this.article.getPrice()));
        this.descriptionLabel.setText(this.article.getDescription());
        this.titleLabel.setText(this.article.getDesignation());
        this.hideButton.setText((this.article.isVisible()) ? "Ne plus afficher" : "Afficher");
        this.imageLabel.setIcon(new ImageIcon(this.article.getImage()));

        if (Connection.getInstance().getConnectedPeople() instanceof Visitor) {
            /*this.editButton.setVisible(false);
            this.statisticsButton.setVisible(false);
            this.hideButton.setVisible(false);*/

            this.initWatch();

            addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent windowEvent){
                    endWatch();
                    dispose();
                }
            });
        }

        setContentPane(root);
        pack();
        setResizable(false);
        setTitle(this.article.getDesignation());
        setVisible(true);

        editButton.addActionListener(this);
        hideButton.addActionListener(this);
        statisticsButton.addActionListener(this);
    }

    private void initWatch() {
        this.watch = new Watch();
        this.watch.setArticle(this.article);
        this.watch.setBeginDate(new Date());
        this.watch.setTour(Connection.getInstance().getTour());
    }

    private void endWatch() {
        this.watch.setEndDate(new Date());

        WatchDAO watchDAO = new WatchDAO();
        watchDAO.addWatch(this.watch);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this.hideButton) {
            ArticleDAO articleDAO = new ArticleDAO();
            this.article.setIsVisible((this.article.isVisible()) ? false : true);

            articleDAO.updateArticle(this.article);
            this.hideButton.setText((this.article.isVisible()) ? "Ne plus afficher" : "Afficher");

        } else if (e.getSource() == editButton) {
            new CreateUpdateArticleForm(this.article);
            this.dispose();

        } else if (e.getSource() == statisticsButton) {
            new StatsArticleForm(this.article);
            this.dispose();
        }
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Watch getWatch() {
        return watch;
    }

    public void setWatch(Watch watch) {
        this.watch = watch;
    }
}
