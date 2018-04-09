package test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import hei.caulier.projet.DataSourceProvider;
import hei.caulier.projet.daos.ArticleDao;
import hei.caulier.projet.entities.Article;

public class ArticleDaotestCase extends AbstractDaoTestCase {
	
private ArticleDao articleDao = new ArticleDao();
	
	@Override
	public void insertDataSet(Statement statement) throws Exception {
		statement.executeUpdate("LOAD DATA LOCAL INFILE\r\n" + 
				"'C:/Users/Thibaut Lamonier/Desktop/HEI4/Projet 100h/divalto/articlesmodif.csv' \r\n" + 
				"INTO TABLE testprojet2.article\r\n" + 
				"FIELDS TERMINATED BY ';'\r\n" + 
				"ENCLOSED BY '' \r\n" + 
				"LINES TERMINATED BY '\\r\\n';");
	}
		
	@Test
	public void shouldListArticles() throws Exception {
		List<Article> articles = articleDao.listArticles();
        Assertions.assertThat(articles).hasSize(529);
        Assertions.assertThat(articles).extracting("idArticle", "refArticle", "nomArticle").contains(
                Assertions.tuple(1, "0000005", "ETIQUETTES DRAPEAUX"),
                Assertions.tuple(2, "0000007", "ETIQUETTE GAM +"),
                Assertions.tuple(3, "0000010", "POLYETHYLENE BD 50µ")
        );
	}
	
	@Test
	public void shouldGetArticle() throws Exception {
		Article article = articleDao.getArticle(42);
	    Assertions.assertThat(article).isNotNull();
	    Assertions.assertThat(article.getIdArticle()).isEqualTo(42);
	    Assertions.assertThat(article.getRefArticle()).isEqualTo("BHD251C");
	    Assertions.assertThat(article.getNomArticle()).isEqualTo("BOLDUC HD 25MM 1 COULEUR");
	}
	
	@Test
	 public void shouldAddArticle() throws Exception {
	    Article newArticle = new Article(null, "ref nouvel article", "nom du nouvel article");
	    articleDao.addArticle(newArticle);
	    try(Connection connection = DataSourceProvider.getDataSource().getConnection();
	            Statement statement = connection.createStatement();
	            ResultSet resultSet = statement.executeQuery("SELECT * FROM article WHERE refArticle='ref nouvel article'")){
	            Assertions.assertThat(resultSet.next()).isTrue();
	            Assertions.assertThat(resultSet.getInt("idArticle")).isNotNull();
	            Assertions.assertThat(resultSet.getString("nomArticle")).isEqualTo("nom du nouvel article");
	            Assertions.assertThat(resultSet.next()).isFalse();
	        }
	}
}
