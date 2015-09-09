package bg.bulsi.internal.controller;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import bg.bulsi.internal.facade.NewsFacade;
import bg.bulsi.internal.facade.TagFacade;
import bg.bulsi.internal.model.News;
import bg.bulsi.internal.model.Tag;

@ManagedBean(name = "controlBean")
@SessionScoped
public class ControlBean implements Serializable {
	
	private static final long serialVersionUID = 6988352663444621787L;
	
	@Inject
	private TagFacade tagFacade;
	
	@Inject
	private NewsFacade newsFacade;
	
	private Collection<News> allNews;
	private News news;
	private int id;
	private Date date;
	
	public void seeAllNews(){
		allNews = newsFacade.getAllNews();
	}
	
	public void filter(){
		allNews = newsFacade.filterNews(date);
	}
	
	public void setNews(News news) {
		this.news = news;
	}
	
	public String updateNews(){
		newsFacade.edit(news);
		return "index";
	}
	
	public Collection<Tag> allTags() {
		return tagFacade.getAllTags();
	}
	
	
	public Collection<News> getAllNews() {
		return allNews;
	}
	
	public void setAllNews(Collection<News> allNews) {
		this.allNews = allNews;
	}
	
	public News getNews() {
		if (news == null) {
			news = newsFacade.find(id);
		}
		else if (news.getId() != id) {
			news = newsFacade.find(id);
		}
		return news;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
