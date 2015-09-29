package bg.bulsi.internal.facade;

import java.util.Collection;
import java.util.Date;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import bg.bulsi.internal.model.News;

@Stateless
public class NewsFacade {
	
	@PersistenceContext(unitName = "jpa-example")
	private EntityManager em;
	
	public NewsFacade() {
		
	}
	
	public void create(News entity) {
		em.persist(entity);
	}
	
	public void edit(News entity) {
		em.merge(entity);
	}
	
	public void remove(News entity) {
		em.remove(em.merge(entity));
	}
	
	public News find(int valueOf){
		return em.find(News.class, valueOf);
	}
	
	@SuppressWarnings("unchecked")
	public Collection<News> filterNews(Date date){
		Query query = em.createNamedQuery("News.selectBefore");
		query.setParameter("date", date);
		
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public Collection<News> getAllNews() {
		Query query = em.createNamedQuery("News.selectAll");
		return query.getResultList();
	}
	
}
