package bg.bulsi.internal.convertors;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import bg.bulsi.internal.facade.TagFacade;
import bg.bulsi.internal.model.Tag;

@Named(value = "tagsConverter")
public class TagsConverter implements Converter {
	
	@Inject
	private TagFacade tf;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || value.isEmpty()) {
			return null;
		}
		return tf.find(Integer.parseInt(value));
	}
	
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null && value instanceof Tag) {
			String result = Integer.toString(((Tag) value).getId());
			return result;
		}
		return null;
	}
}
