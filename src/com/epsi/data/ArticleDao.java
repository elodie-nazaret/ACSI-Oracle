package com.epsi.data;

import java.util.List;

public interface ArticleDao {

    public List<Article> getAllArticle();
    public Article getArticle(int id);
    public void updateArticle(Article article);
    public void deleteArticle(Article article);
}
