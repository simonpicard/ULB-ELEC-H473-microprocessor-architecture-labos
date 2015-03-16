package softarch.portal.app;

import java.util.Map;

import softarch.portal.data.UserProfile;
import softarch.portal.db.DatabaseException;
import softarch.portal.db.DatabaseFacade;

import java.util.Hashtable;
import java.util.List;
import java.util.Vector;
import java.util.Iterator;
import java.util.Date;

/**
 * This class handles all requests concerning the portal's users (for example
 * logging in or logging out).
 * @author Niels Joncheere
 */
public class UserManager extends Manager {
	/**
	 * Holds the session ID for each user that is currently logged in;
	 * this is a mapping from usernames to session IDs.
	 */
	private Map users;

	/**
	 * Holds the session ID that will be issued to the next user that logs
	 * in; this number will just be incremented each time a user logs in.
	 */
	private Number sessionId;

	/**
	 * Increments the session ID.
	 * Note that this is a post-increment (could be defined informally
	 * as <code>sessionId++</code>).
	 */
	private Number incrementSessionId() {
		Number oldSessionId = sessionId;
		sessionId = new Integer(sessionId.intValue() + 1);
		return oldSessionId;
	}

	/**
	 * Creates a new user manager.
	 * @param dbFacade	The database facade the manager should
	 * 			use to access the portal's database.
	 */
	public UserManager(DatabaseFacade dbFacade) {
		// Initialize the superclass' members:
		this.dbFacade	= dbFacade;
		// Initialize the class' members:
		this.users	= new Hashtable();
		this.sessionId	= new Integer(0);
	}

	/**
	 * Adds a user profile to the user database.
	 * @param profile The profile to be added.
	 */
	public void add(UserProfile profile)
		throws ApplicationException {

		try {
			String username = profile.getUsername();
			if (dbFacade.userExists(username))
				throw new DatabaseException(
					"The username \"" + username + "\" " +
					"is already taken!");
			else
				dbFacade.insert(profile);
		}
		catch (DatabaseException e) {
			throw new ApplicationException(e.getMessage());
		}
		catch (Exception e) {
			throw new ApplicationException(
				"The user manager has caught an unexpected " +
				"exception: " + e.getMessage());
		}
	}

	/**
	 * Returns the user profile for the user with the specified username.
	 */
	public UserProfile findUser(String username)
		throws ApplicationException {

		try {
			return dbFacade.findUser(username);
		}
		catch (DatabaseException e) {
			throw new ApplicationException(e.getMessage());
		}
		catch (Exception e) {
			throw new ApplicationException(
				"The user manager has caught an unexpected " +
				"exception: " + e.getMessage());
		}
	}

	/**
	 * Returns the user profile for the user with the specified username,
	 * and throws an exception if his session ID does not match the one that
	 * is provided as a parameter.
	 */
	public UserProfile findUser(String username, Number sessionId)
		throws ApplicationException {

		if (users.containsKey(username)
			&& ((Number) users.get(username)).equals(sessionId))
			
			return findUser(username);
		else
			throw new ApplicationException(
				"Invalid username \"" + username + "\" " +
				"and/or session ID " + sessionId + "!");
	}

	/**
	 * Logs in the user with the specified username and password.
	 */
	public Number login(String username, String password)
		throws ApplicationException {

		try {
			if (findUser(username).getPassword().equals(password)) {
				if (users.containsKey(username))
					return (Number) users.get(username);
				else {
					users.put(username, sessionId);
					return incrementSessionId();
				}
			}
			else
				throw new ApplicationException(
					"Invalid password!");
		}
		catch (ApplicationException e) {
			throw new ApplicationException(e.getMessage());
		}
		catch (Exception e) {
			throw new ApplicationException(
				"The user manager has caught an unexpected " +
				"exception: " + e.getMessage());
		}
	}

	/**
	 * Returns a list of all users that are currently logged in.
	 */
	public List getActiveUsers()
		throws ApplicationException {

		try {
			List result = new Vector();
			for (Iterator i = users.keySet().iterator();
				i.hasNext(); ) {
				
				String username = (String) i.next();	
				result.add(findUser(username));
			}
			return result;
		}
		catch (ApplicationException e) {
			throw new ApplicationException(e.getMessage());
		}
		catch (Exception e) {
			throw new ApplicationException(
				"The user manager has caught an unexpected " +
				"exception: " + e.getMessage());
		}
	}

	/**
	 * Logs out the user with the specified username and session ID.
	 */
	public void logout(String username, Number sessionId)
		throws ApplicationException {

		try {
			if (users.containsKey(username)) {
				UserProfile profile
					= findUser(username, sessionId);
				dbFacade.update(
					profile.setLastLogin(new Date()));
				users.remove(username);
			}
			else
				throw new ApplicationException(
					"The user \"" + username + "\" is " +
					"not logged in!");
		}
		catch (DatabaseException e) {
			throw new ApplicationException(e.getMessage());
		}
	}
}
