package bg.bulsi.internal.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import bg.bulsi.internal.model.User;

@Stateless
public class UsersFacade {
	
	@PersistenceContext(unitName = "jpa-example")
	EntityManager em;
	
	public void create(User entity) {
		em.persist(entity);
	}
	
	public void edit(User entity) {
		em.merge(entity);
	}
	
	public void remove(User entity) {
		em.remove(em.merge(entity));
	}
	
	public User getUserByUsername(String username) {
		return (User) em.createNamedQuery("User.findByUsername").setParameter("username", username).getResultList().get(0);
	}
	
}
