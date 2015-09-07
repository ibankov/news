package bg.bulsi.internal.facade;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import bg.bulsi.internal.model.Tag;

@Stateless
public class TagFacade {
	
	@PersistenceContext(unitName = "jpa-example")
	EntityManager em;
	
	public TagFacade() {
		
	}
	
	private EntityManager getEntityManager() {
		return em;
	}
	
	public void create(Tag entity) {
		getEntityManager().persist(entity);
	}
	
	public void edit(Tag entity) {
		getEntityManager().merge(entity);
	}
	
	public void remove(Tag entity) {
		getEntityManager().remove(getEntityManager().merge(entity));
	}
	
	@SuppressWarnings("unchecked")
	public Collection<Tag> getAllTags() {
		Query query = em.createNamedQuery("Tags.selectAll");
		Collection<Tag> resultList = (Collection<Tag>) query.getResultList();
		return resultList;
	}

	public Tag find(int valueOf) {
		Tag tag = getEntityManager().find(Tag.class,valueOf );
		return tag;
	}
}
