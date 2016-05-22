package app;

import java.util.List;

import jello.model.JelloEntity;
import jello.model.JelloModel;
import jello.security.Accessible;
import jello.rest.IllegalRequestResource;
import jello.security.Role;

import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import jello.annotation.Association;
import jello.annotation.Expose;
import jello.annotation.Attachment;
import jello.annotation.Required;

@PersistenceCapable
@Accessible(Role.ALL)
public class AppUser extends JelloEntity {

	private static final long serialVersionUID = -7687098366305046295L;

	@Expose
	@Required
	public String id;

	@Attachment(accept = "image/*")
	@Expose
	public String profile;

	// @Attachment
	// @Expose
	// public String file;

	@NotPersistent
	@Association(mappedBy = "postUserId")
	@Expose
	private List<Log> logs;
	
	@Accessible
	public static void createUser() throws IllegalRequestResource {
		User user = UserServiceFactory.getUserService().getCurrentUser();
		List<AppUser> list =(List<AppUser>)JelloModel.select(AppUser.class);
		if(user!=null){
			boolean isInList = false;
			for(int i=0;i<list.size();i++){
				if(user.getEmail().equals(list.get(i).id)){
					isInList = true;
				}	
				
			}
			if(!isInList){
				AppUser newUser = new AppUser();
				newUser.id = user.getEmail();
				newUser.create();
			}
		}
	}

	@Override
	protected JelloEntity beforeSave() throws IllegalRequestResource {
		User user = UserServiceFactory.getUserService().getCurrentUser();
		this.id = user.getEmail();
		return this;
	}
}
