package bg.bulsi.internal.controller;

import java.io.Serializable;
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

import bg.bulsi.internal.facade.UsersFacade;
import bg.bulsi.internal.model.User;

@Named("loginBean")
@SessionScoped
public class LoginBean implements Serializable {
	
	private static final long serialVersionUID = -5965792035722234028L;

	@Inject
	private UsersFacade usersFacade;

	private String username;
	private String password;
	private User user;

	public String login() {
		ResourceBundle rbBundle = ResourceBundle.getBundle("SystemMessages",
				Locale.ENGLISH);
		HttpServletRequest request = getRequest();
		try {
			request.login(username, password);
		} catch (ServletException e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, rbBundle
							.getString("wrongInfo"), "fail"));
			e.printStackTrace();
			return "";
		}
		return "index";
	}

	public String logout() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();
		try {
			request.logout();
			request.getSession(false).invalidate();
			if (request.getRequestURL().toString()
					.equals("http://10.10.0.113:8080/news/index.xhtml")
					|| request.getRequestURL().toString()
							.equals("http://localhost:8080/news/index.xhtml")
					|| request.getRequestURL().toString()
							.equals("http://127.0.0.1:8080/news/index.xhtml")) {
				return "login";
			}
			return "index";
		} catch (ServletException e) {
			context.addMessage(null, new FacesMessage("Logout failed."));
		}
		return "";
	}

	private HttpServletRequest getRequest() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		Object request = externalContext.getRequest();
		return request instanceof HttpServletRequest
				? (HttpServletRequest) request
				: null;
	}

	public User getUser() {
		if (user == null) {
			user = usersFacade.getUserByUsername(username);
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
