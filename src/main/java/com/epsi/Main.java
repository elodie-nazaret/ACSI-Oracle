package com.epsi;

import com.epsi.entities.ArticleDAO;
import com.epsi.forms.ArticleForm;
import com.epsi.forms.CreateUpdateArticleForm;
import com.epsi.forms.HomeForm;
import com.epsi.forms.SubscribeForm;

import java.lang.String;

public class Main {

    public static void main(String[] args) {
        ArticleDAO articleDAO = new ArticleDAO();
//        new CreateUpdateArticleForm();
//        new CreateUpdateArticleForm(articleDAO.getArticleById(21));
//        new ArticleForm(articleDAO.getArticleById(21));
        new HomeForm();
    }
}
