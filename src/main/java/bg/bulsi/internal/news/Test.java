package bg.bulsi.internal.news;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;

import java.io.Serializable;

@ManagedBean(name = "test")
@SessionScoped
public class Test implements Serializable {
	
	private static final long serialVersionUID = 8424957023957186438L;
	
	private String field = "kon";
	
	public void showValues() {
		System.out.println("Value of the field is : " + field);
	}
	
	public String getField() {
		return field;
	}
	
	public void setField(String field) {
		this.field = field;
	}
}