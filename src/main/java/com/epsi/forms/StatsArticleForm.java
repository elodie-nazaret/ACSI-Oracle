package com.epsi.forms;

import com.epsi.entities.Article;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StatsArticleForm extends JFrame implements ActionListener {

    private Article article;

    public StatsArticleForm(Article article) {
        this.article = article;
    }


    public void actionPerformed(ActionEvent e) {

    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
