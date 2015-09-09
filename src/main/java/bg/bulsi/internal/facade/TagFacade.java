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
	
	public void create(Tag entity) {
		em.persist(entity);
	}
	
	public void edit(Tag entity) {
		em.merge(entity);
	}
	
	public void remove(Tag entity) {
		em.remove(em.merge(entity));
	}
	
	@SuppressWarnings("unchecked")
	public Collection<Tag> getAllTags() {
		Query query = em.createNamedQuery("Tags.selectAll");
		return (Collection<Tag>) query.getResultList();
	}
	
	public Tag find(int valueOf) {
		return em.find(Tag.class, valueOf);
	}
}
