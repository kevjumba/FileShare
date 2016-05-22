package app;

import java.util.List;

import jello.model.JelloEntity;
import jello.security.Accessible;
import jello.rest.IllegalRequestResource;
import jello.security.Role;

import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserServiceFactory;

import jello.annotation.Association;
import jello.annotation.Expose;
import jello.annotation.Attachment;
import jello.annotation.KeyElement;

@PersistenceCapable
@Accessible(Role.ALL)
public class Post extends JelloEntity {

	private static final long serialVersionUID = -8174567808753511457L;

	public String owner;

	@Attachment
	@Expose
	public String file;
	
	@Expose
	public String title;

	@NotPersistent
	@Association(mappedBy = "postId")
	@Expose
	private List<Log> logs;

	protected JelloEntity beforeSave() throws IllegalRequestResource {
		User user=UserServiceFactory.getUserService().getCurrentUser();
		owner = user.getEmail();
		return this;
	}
	
}
