package bg.bulsi.internal.convertors;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import bg.bulsi.internal.facade.RolesFacade;
import bg.bulsi.internal.model.UserRoles;

@Named(value = "roleConverter")
public class RoleConverter implements Converter {
	
	@Inject
	private RolesFacade rf;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || value.isEmpty()) {
			return null;
		}
		return rf.find(Integer.parseInt(value));
	}
	
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null && value instanceof UserRoles) {
			String result = Integer.toString(((UserRoles) value).getId());
			return result;
		}
		return null;
	}
}
