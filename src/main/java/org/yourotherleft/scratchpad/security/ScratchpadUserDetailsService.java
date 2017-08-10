package org.yourotherleft.scratchpad.security;

import static java.util.Objects.requireNonNull;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.yourotherleft.scratchpad.repository.UserRepository;

/**
 * Tells Spring Security how to look up users for authentication.
 *
 * @author jallen
 */
@Service
public class ScratchpadUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;

	@Autowired
	public ScratchpadUserDetailsService(final UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		requireNonNull(username, "username is null");

		final org.yourotherleft.scratchpad.entity.User scratchpadUser =
			requireNonNull(userRepository.findByUsername(username), String.format("no user for username [%s]", username));

		return new User(scratchpadUser.getUsername(), scratchpadUser.getPassword(), Lists.newArrayList());
	}

}
