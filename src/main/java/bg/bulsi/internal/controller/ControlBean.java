package bg.bulsi.internal.controller;

import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import bg.bulsi.internal.facade.NewsFacade;
import bg.bulsi.internal.facade.RolesFacade;
import bg.bulsi.internal.facade.TagFacade;
import bg.bulsi.internal.model.News;
import bg.bulsi.internal.model.Tag;
import bg.bulsi.internal.model.UserRoles;

@ManagedBean(name = "controlBean")
@SessionScoped
public class ControlBean implements Serializable {
	
	private static final long serialVersionUID = 6988352663444621787L;
	
	@Inject
	private TagFacade tf;
	@Inject
	private NewsFacade nf;
	@Inject
	private RolesFacade rf;
	
	private Collection<News> allNews;
	private News news;
	private int id;
	private Date date;
	
	public void goToNews() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		try {
			externalContext.redirect("/news/edit/addNews.xhtml");
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void goToRegister() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		try {
			externalContext.redirect("/news/register.xhtml");
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void goToLogin() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		try {
			externalContext.redirect("/news/login.xhtml");
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void goToTags() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		try {
			externalContext.redirect("/news/edit/addTag.xhtml");
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void goToAllNews() {
		allNews = nf.getAllNews();
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		try {
			externalContext.redirect("/news/view/allNews.xhtml");
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void seeAllNews(){
		allNews = nf.getAllNews();
	}
	
	public void seeAllNew(Date date){
		Collection<News> allNewsTemp = nf.getAllNews();
		DateFormat sdf = new SimpleDateFormat("dd MMM yyyy HH:mm");
		allNews = new LinkedList<News>();
		Date d1 = null;
		
		
		for (News news : allNewsTemp) {
			try {
				d1 = sdf.parse(news.getDate());
			}
			catch (ParseException e) {
				e.printStackTrace();
			}
			if(date.before(d1)){
				allNews.add(news);
			}
		}
	}
	
//	public void goToAllNews(Date date) {
//		Collection<News> allNewsTemp = nf.getAllNews();
//		DateFormat sdf = new SimpleDateFormat("dd MMM yyyy HH:mm");
//		allNews = new LinkedList<News>();
//		Date d1 = null;
//		
//		
//		for (News news : allNewsTemp) {
//			try {
//				d1 = sdf.parse(news.getDate());
//			}
//			catch (ParseException e) {
//				e.printStackTrace();
//			}
//			if(date.before(d1)){
//				allNews.add(news);
//			}
//		}
//		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
//		try {
//			externalContext.redirect("view/allNews.xhtml");
//		}
//		catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
	
	public void setNews(News news) {
		this.news = news;
	}
	
	
	public Collection<Tag> allTags() {
		return tf.getAllTags();
	}
	
	public Collection<UserRoles> allRoles() {
		return rf.getAllRoles();
	}
	
	public Collection<News> getAllNews() {
		return allNews;
	}
	
	public void setAllNews(Collection<News> allNews) {
		this.allNews = allNews;
	}
	
	public News getNews() {
		if (news == null) {
			news = nf.find(id);
		}
		else if (news.getId() != id) {
			news = nf.find(id);
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

	private boolean sortAscending = true;
	
	public String sortByDate() {
		allNews = nf.getAllNews();
		
		if (sortAscending) {
			
			//ascending order
			Collections.sort((List<News>) allNews, new Comparator<News>() {
				
				@Override
				public int compare(News n1, News n2) {
					DateFormat df = new SimpleDateFormat("dd MMM yyyy HH:mm");
					Date d1 = null;
					Date d2 = null;
					try {
						d2 = df.parse(n2.getDate());
						d1 = df.parse(n1.getDate());
					}
					catch (ParseException e) {
						e.printStackTrace();
					}
					
					if (d1.before(d2)) {
						return -1;
					}
					else if (d1.after(d2)) {
						return 1;
					}
					else {
						return 0;
					}
				}
				
			});
			sortAscending = false;
			
		}
		else {
			
			//descending order
			Collections.sort((List<News>) allNews, new Comparator<News>() {
				
				@Override
				public int compare(News n1, News n2) {
					DateFormat df = new SimpleDateFormat("dd MMM yyyy HH:mm");
					Date d1 = null;
					Date d2 = null;
					try {
						d2 = df.parse(n2.getDate());
						d1 = df.parse(n1.getDate());
					}
					catch (ParseException e) {
						e.printStackTrace();
					}
					
					if (d1.before(d2)) {
						return 1;
					}
					else if (d1.after(d2)) {
						return -1;
					}
					else {
						return 0;
					}
					
				}
				
			});
			sortAscending = true;
		}
		
		return null;
	}
	
}
