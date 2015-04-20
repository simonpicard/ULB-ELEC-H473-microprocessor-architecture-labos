package softarch.portal.db;

import softarch.portal.data.UserProfile;

public interface UserDatabase {

	/**
	 * Inserts a new user profile into the user database.
	 */
	public void insert(UserProfile profile) throws DatabaseException;
	/**
	 * Updates an existing user profile in the user database.
	 */
	public void update(UserProfile profile) throws DatabaseException;
	/**
	 * Returns the user with the specified username.
	 */
	public UserProfile findUser(String username) throws DatabaseException;
	/**
	 * Checks whether a user with the specified username exists.
	 */
	public boolean userExists(String username) throws DatabaseException;
}
