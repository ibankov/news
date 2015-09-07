package bg.bulsi.internal.controller;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.util.Base64;

import bg.bulsi.internal.facade.UsersFacade;
import bg.bulsi.internal.model.User;

@Named("loginBean")
@SessionScoped
public class LoginBean implements Serializable {
	
	private static final long serialVersionUID = -5965792035722234028L;
	
	@Inject
	private UsersFacade uf;
	private String username;
	private String password;
	private User user;
	
	public String login() {
		ResourceBundle rbBundle = ResourceBundle.getBundle("SystemMessages",Locale.ENGLISH);
		
		HttpServletRequest request = getRequest();
		MessageDigest md = null;
		
		try {
			md = MessageDigest.getInstance("MD5");
			md.reset();
			md.update(password.getBytes(StandardCharsets.UTF_8));
			String hashPass = Base64.encodeToString(md.digest(), false);
			request.login(username, password);
		}
		catch (ServletException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,rbBundle.getString("wrongInfo"), "fail"));
			e.printStackTrace();
			return "";
		}
		catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return "index";
	}
	
	public String logout() {
		
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
		try {
			
			request.logout();
			
			request.getSession(false).invalidate();
			
			return "logout";
			
		}
		catch (ServletException e) {
			
			context.addMessage(null, new FacesMessage("Logout failed."));
		}
		return "";
	}
	
	private HttpServletRequest getRequest() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		Object request = externalContext.getRequest();
		return request instanceof HttpServletRequest ? (HttpServletRequest) request : null;
	}
	
	public User getUser() {
		if (user == null) {
			user = uf.getUserByUsername(username);
		}
		
		return user;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
}
