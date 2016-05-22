package app;

import jello.model.JelloEntity;
import jello.security.Accessible;
import jello.rest.IllegalRequestResource;
import jello.security.Role;

import javax.jdo.annotations.PersistenceCapable;
import jello.annotation.Expose;
import com.google.appengine.api.datastore.Key;
import jello.ux.Preview;
import jello.annotation.Reference;

@PersistenceCapable
@Accessible(Role.ALL)
public class Log extends JelloEntity {

	private static final long serialVersionUID = -4835247524433554432L;

	@Expose
	public String user;

	@Expose
	public Double latitude;

	@Expose
	public Double longitude;

	public Post post;
	
	@Reference(Post.class)
	@Preview(element = "title")
	@Expose
	public Key postId;

	@Reference(AppUser.class)
	@Preview(element = "id")
	@Expose
	public Key postUserId;

	@Override
	protected JelloEntity beforeSave() throws IllegalRequestResource {
		return this;
	}

}
