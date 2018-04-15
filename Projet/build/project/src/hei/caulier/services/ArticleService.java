package hei.caulier.services;

import java.util.List;

import hei.caulier.projet.daos.ArticleDao;
import hei.caulier.projet.entities.Article;

public class ArticleService {

	private ArticleDao articleDao = new ArticleDao();
    private static class ArticleServiceHolder {
        private static ArticleService instance = new ArticleService();
    }
    
    public static ArticleService getInstance() {
        return ArticleServiceHolder.instance;
    }

    private ArticleService() {
    }

    public List<Article> listAllArticles() {
            return articleDao.listArticles();
    }
    public Article getArticle(Integer id) {
        if(id == null) {
            throw new IllegalArgumentException("L'id de l'article n'est pas specifie");
        }
        return articleDao.getArticle(id);
    }
    
    public Integer getArticleId(Article article) {
        return articleDao.getArticleId(article);
    }
    public Integer getArticleIdFromString(String codeArticle) {
        return articleDao.getArticleIdFromString(codeArticle);
    }

    public void addArticle(Article newArticle) {
        if(newArticle == null){
            throw new IllegalArgumentException("Un article doit etre fourni");
        }
        if(newArticle.getRefArticle() == null || "".equals(newArticle.getRefArticle())) {
            throw new IllegalArgumentException("Un article doit avoir une reference");
        }
        if(newArticle.getNomArticle() == null || "".equals(newArticle.getNomArticle())) {
            throw new IllegalArgumentException("Un article doit avoir un nom");
        }

        articleDao.addArticle(newArticle);
    }
}
