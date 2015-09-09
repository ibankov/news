package bg.bulsi.internal.facade;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import bg.bulsi.internal.model.UserRoles;

@Stateless
public class RolesFacade {
	
	@PersistenceContext(unitName = "jpa-example")
	EntityManager em;
	
	public void create(UserRoles entity) {
		em.persist(entity);
	}
	
	public void edit(UserRoles entity) {
		em.merge(entity);
	}
	
	public void remove(UserRoles entity) {
		em.remove(em.merge(entity));
	}
	
	public UserRoles find(int valueOf) {
		return em.find(UserRoles.class, valueOf);
	}
	
	@SuppressWarnings("unchecked")
	public Collection<UserRoles> getAllRoles() {
		Query query = em.createNamedQuery("Roles.selectAll");
		return (Collection<UserRoles>) query.getResultList();
	}

}
