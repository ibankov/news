package bg.bulsi.internal.controller;

import java.io.Serializable;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import bg.bulsi.internal.facade.TagFacade;
import bg.bulsi.internal.model.Tag;

@ManagedBean(name = "tagsBean")
@SessionScoped
public class TagsBean implements Serializable {
	
	private static final long serialVersionUID = -1503958244560243143L;
	
	private Tag tag = new Tag();
	
	@Inject
	private TagFacade tf;
	
	public String addTag() {
		ResourceBundle rbBundle = ResourceBundle.getBundle("SystemMessages", Locale.ENGLISH);
		tf.create(tag);
		tag = new Tag();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, rbBundle.getString("addTag"), "success"));
		return "index";
	}
	
	public Tag getTag() {
		return tag;
	}
	
	public void setTag(Tag tag) {
		this.tag = tag;
	}
	
}
