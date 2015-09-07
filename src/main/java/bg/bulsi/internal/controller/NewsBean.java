package bg.bulsi.internal.controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import bg.bulsi.internal.facade.NewsFacade;
import bg.bulsi.internal.model.News;

@ManagedBean(name = "newsBean")
@SessionScoped

public class NewsBean implements Serializable {
	
	private static final long serialVersionUID = -1006360471153400088L;
	
	private News news = new News();
	
	@Inject private NewsFacade nf;

	public String addNews(){
		ResourceBundle rbBundle = ResourceBundle.getBundle("SystemMessages",Locale.ENGLISH);
		SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy HH:mm");
		
		Date today = new Date();
		String date = sdf.format(today);
		news.setDate(date);
		
		nf.edit(news);
		
		news = new News();
		
		FacesContext.getCurrentInstance().addMessage(
				null, new FacesMessage(FacesMessage.SEVERITY_INFO, rbBundle.getString("addNews"),
						"success"));
		
		return "index";
	}
	
	public News getNews() {
		return news;
	}
	
	public void setNews(News news) {
		this.news = news;
	}
	
	
}
