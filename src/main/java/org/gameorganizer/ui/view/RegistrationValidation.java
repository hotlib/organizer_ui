package org.gameorganizer.ui.view;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;
import javax.persistence.Query;

import org.gameorganizer.ui.entity.Player;
import org.gameorganizer.ui.service.PlayerService;

@Named
@RequestScoped
public class RegistrationValidation implements Serializable{
	
	@EJB
	PlayerService playerService;

	public void checkEmail(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {

		if (playerService.isEmailRegistered((String) value))
			throw new ValidatorException(new FacesMessage("E-mail " + value
					+ " already exists"));

	}

	public void checkNickname(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {

		if (playerService.checkNickname((String) value))
			throw new ValidatorException(new FacesMessage("Nickname " + value
					+ " already exists"));

	}

}
