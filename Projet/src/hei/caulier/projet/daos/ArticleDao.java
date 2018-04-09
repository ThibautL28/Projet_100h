package hei.caulier.projet.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import hei.caulier.projet.DataSourceProvider;
import hei.caulier.projet.entities.Article;
import hei.caulier.projet.exceptions.ProjectRuntimeException;

public class ArticleDao {

	public List<Article> listArticles() {
    	List<Article> articles = new ArrayList<>();

        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM article ORDER BY idArticle")) {
            while (resultSet.next()) {
                articles.add(new Article(
                        resultSet.getInt("idArticle"),
                        resultSet.getString("refArticle"),
                        resultSet.getString("nomArticle")
                ));
            }
        } catch (SQLException e) 
        {
			throw new ProjectRuntimeException("Erreur en essayant de lister les articles", e);
		}
    	return articles;
    }
	
	public Article getArticle(Integer id) {
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM article WHERE idArticle = ?")) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    return new Article(
                    		 resultSet.getInt("idArticle"),
                             resultSet.getString("refArticle"),
                             resultSet.getString("nomArticle"));
                }
            }
        } catch (SQLException e) 
        {
			throw new ProjectRuntimeException("Erreur en essayant d'obtenir l'article", e);
        }
    	return null;
    }
	
	public void addArticle(Article newArticle) {
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO article(refArticle, nomArticle) VALUES (?, ?)")) {
            statement.setString(1, newArticle.getRefArticle());
            statement.setString(2, newArticle.getNomArticle());
            statement.executeUpdate();
        } catch (SQLException e) {
			throw new ProjectRuntimeException("Erreur en essayant d'ajouter un article", e);
		}
    }
	
	public Integer getArticleId(Article article){
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM article WHERE refArticle = ?")) {
            statement.setString(1, article.getRefArticle());
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    return resultSet.getInt("idArticle");
                }
            }
        } catch (SQLException e) {
            throw new ProjectRuntimeException("Erreur en essayant d'obtenir l'id de l'article", e);
        }
        return null;
    }
}
