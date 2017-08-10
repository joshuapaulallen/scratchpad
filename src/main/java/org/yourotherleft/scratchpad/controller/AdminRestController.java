package org.yourotherleft.scratchpad.controller;

import static com.google.common.base.Preconditions.checkNotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.yourotherleft.scratchpad.entity.User;
import org.yourotherleft.scratchpad.repository.UserRepository;
import org.yourotherleft.scratchpad.type.Status;

/**
 * Defines REST API for admin-related actions.
 *
 * @author jallen
 */
@RestController
@RequestMapping(value = "/admin")
public class AdminRestController {

	private static final Logger LOG = LoggerFactory.getLogger(AdminRestController.class);

	private final UserRepository userRepository;

	@Autowired
	public AdminRestController(final UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	/**
	 * A ping endpoint that can be used for a health check.
	 *
	 * @return {@link Status} of the application.
	 */
	@RequestMapping(value = "/ping", method = RequestMethod.GET)
	public Status ping() {
		// log it
		LOG.info("received ping");

		// return "ok"
		return new Status("OK");
	}

	/**
	 * Saves a new user with the given contents.
	 *
	 * @param user The user to be saved.
	 * @return The saved note.
	 */
	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public User createUser(@RequestBody final User user) {
		checkNotNull(user, "user is null");

		// log it
		LOG.info(String.format("request to add user [%s]", user));

		// save and return
		return userRepository.save(user);
	}

	/**
	 * Retrieve a user with the given id.
	 *
	 * @param id The id.
	 * @return The user with the given id.
	 */
	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
	public User getUser(@PathVariable final Integer id) {
		checkNotNull(id, "id is null");

		// log it
		LOG.info(String.format("request to get user with id [%s]", id));

		// retrieve and return the user, or throw if it doesn't exist
		return checkNotNull(userRepository.findOne(id), "could not find user with id [%s]", id);
	}

}
