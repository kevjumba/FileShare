package app;

import jello.annotation.Expose;
import jello.annotation.Reference;
import jello.annotation.Required;
import jello.model.JelloEntity;
import jello.security.Accessible;
import jello.rest.IllegalRequestResource;
import jello.security.Role;
import jello.ux.Preview;

import javax.jdo.annotations.PersistenceCapable;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
@Accessible( Role.ALL )public class Message extends JelloEntity {

	private static final long serialVersionUID = -5431069975035748233L;

	@Expose
	@Required
	public String message;
	
	@Reference(AppUser.class)
	@Preview(element="id")
	@Expose
	@Required
	public Key sender;
	
	@Reference(AppUser.class)
	@Preview(element="id")
	@Expose
	@Required
	public Key receiver;
	
	protected JelloEntity beforeSave() throws IllegalRequestResource {
		return this;
	}



}
