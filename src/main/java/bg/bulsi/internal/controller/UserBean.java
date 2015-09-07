package bg.bulsi.internal.controller;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.util.Base64;

import bg.bulsi.internal.facade.RolesFacade;
import bg.bulsi.internal.facade.UsersFacade;
import bg.bulsi.internal.model.User;
import bg.bulsi.internal.model.UserRoles;

@ManagedBean(name = "userBean")
@SessionScoped
public class UserBean implements Serializable {
	
	private static final long serialVersionUID = -7245470548466151742L;
	
	@Inject
	private UsersFacade uf;
	@Inject private RolesFacade rf;
	
	private User user = new User();
	private List<UserRoles> ur = new ArrayList<UserRoles>();
	private String confirmPassword;
	
	
	public String registerUser() {
		ResourceBundle rbBundle = ResourceBundle.getBundle("SystemMessages",Locale.ENGLISH);
		if(!(confirmPassword.equals(user.getPassword()))){
			FacesContext.getCurrentInstance().addMessage(
					null, new FacesMessage(FacesMessage.SEVERITY_INFO,rbBundle.getString("wrongPasswords"),
							"fail"));
			return ""; 
		}
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
			md.reset();
		}
		catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		try {
			md.update(user.getPassword().getBytes("UTF-8"));
		}
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
//		String hashedPassword = Util.createPasswordHAsh
		String newPass = Base64.encodeToString(md.digest(), false);
//		String newPass = String.format("%032x", new BigInteger(md.digest()));
//		user.setPassword(newPass);
		for (UserRoles userRoles : ur) {
			rf.edit(userRoles);
		}
		uf.edit(user);
		
		user = new User();
		
		FacesContext.getCurrentInstance().addMessage(
				null, new FacesMessage(FacesMessage.SEVERITY_INFO, rbBundle.getString("registerSuccess"),
						"success"));
		
		return "index";
	}
	
	
	public String getConfirmPassword() {
		return confirmPassword;
	}
	
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public List<UserRoles> getUr() {
		return ur;
	}
	
	public void setUr(List<UserRoles> ur) {
		this.ur = ur;
	}
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
}
