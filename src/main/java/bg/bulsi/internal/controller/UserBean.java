package bg.bulsi.internal.controller;

import java.io.Serializable;
import java.util.Collection;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import bg.bulsi.internal.facade.RolesFacade;
import bg.bulsi.internal.facade.UsersFacade;
import bg.bulsi.internal.model.User;
import bg.bulsi.internal.model.UserRoles;

@ManagedBean(name = "userBean")
@SessionScoped
public class UserBean implements Serializable {
	
	private static final long serialVersionUID = -7245470548466151742L;
	
	@Inject
	private UsersFacade usersFacade;
	
	@Inject
	private RolesFacade rolesFacade;
	
	private User user = new User();
	private String confirmPassword;
	private Collection<UserRoles> allRoles;
	
	public String registerUser() {
		ResourceBundle rbBundle = ResourceBundle.getBundle("SystemMessages", Locale.ENGLISH);
		if (!(confirmPassword.equals(user.getPassword()))) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, rbBundle.getString("wrongPasswords"), "fail"));
			return "";
		}
		usersFacade.edit(user);
		user = new User();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, rbBundle.getString("registerSuccess"), "success"));
		return "index";
	}
	
	
	public Collection<UserRoles> getAllRoles() {
		if (allRoles == null) {
			allRoles = rolesFacade.getAllRoles();
		}
		return allRoles;
	}


	public void setAllRoles(Collection<UserRoles> allRoles) {
		this.allRoles = allRoles;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}
	
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
}
