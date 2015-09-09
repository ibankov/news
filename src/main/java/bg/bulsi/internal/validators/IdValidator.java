package bg.bulsi.internal.validators;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;

import bg.bulsi.internal.facade.NewsFacade;

@Named(value = "idValidator")
public class IdValidator implements Validator {
	
	@Inject
	private NewsFacade newsFacade;
	
	@Override
	public void validate(FacesContext context, UIComponent component, Object value)
			throws ValidatorException {
		
		if (newsFacade.find(Integer.parseInt(value.toString())) == null) {
			try {
				context.getExternalContext().responseSendError(403, 
						(String) component.getAttributes().get("validatorMessage"));
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
