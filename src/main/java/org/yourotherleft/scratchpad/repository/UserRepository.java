package org.yourotherleft.scratchpad.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.yourotherleft.scratchpad.entity.User;

/**
 * A repository for CRUD operations on {@link User}s.
 *
 * @author jallen
 */
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

	/**
	 * Fetch users by username.
	 *
	 * @param username The username.
	 * @return The user with the given username.
	 */
	@Query("select u from User u where u.username = :username")
	User findByUsername(@Param("username") String username);

	/**
	 * Fetch users by api key.
	 *
	 * @param apiKey The api key.
	 * @return The user with the given api key.
	 */
	@Query("select u from User u where u.apiKey = :apiKey")
	User findByApiKey(@Param("apiKey") String apiKey);

}
