package com.epsi;

import com.epsi.entities.ArticleDAO;
import com.epsi.forms.ConnectionForm;

public class Main {

    public static void main(String[] args) {
        ArticleDAO articleDAO = new ArticleDAO();
//        new CreateUpdateArticleForm();
//        new CreateUpdateArticleForm(articleDAO.getArticleById(21));
//        new ArticleForm(articleDAO.getArticleById(21));
//        new HomeForm();
        new ConnectionForm();
    }
}
