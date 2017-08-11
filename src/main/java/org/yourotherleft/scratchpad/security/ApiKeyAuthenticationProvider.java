package org.yourotherleft.scratchpad.security;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.yourotherleft.scratchpad.repository.UserRepository;

@Component
public class ApiKeyAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

	private final UserRepository userRepository;

	@Autowired
	public ApiKeyAuthenticationProvider(final UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		// nothing to check
	}

	@Override
	protected UserDetails retrieveUser(final String apiKey, final UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		final org.yourotherleft.scratchpad.entity.User scratchpadUser = userRepository.findByApiKey(apiKey);

		if (scratchpadUser == null) {
			throw new UsernameNotFoundException(String.format("no user for api key [%s]", apiKey));
		}

		return new User(scratchpadUser.getUsername(), scratchpadUser.getPassword(), Lists.newArrayList());
	}

}
