package org.yourotherleft.scratchpad.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.yourotherleft.scratchpad.security.ApiKeyAuthenticationProvider;
import org.yourotherleft.scratchpad.security.ScratchpadUserDetailsService;

/**
 * Defines security for the application.
 *
 * @author jallen
 */
@Configuration
@EnableWebSecurity
public class ScratchpadSecurityConfiguration extends WebSecurityConfigurerAdapter {

	private final ScratchpadUserDetailsService scratchpadUserDetailsService;
	private final ApiKeyAuthenticationProvider apiKeyAuthenticationProvider;

	@Autowired
	public ScratchpadSecurityConfiguration(final ScratchpadUserDetailsService scratchpadUserDetailsService, final ApiKeyAuthenticationProvider apiKeyAuthenticationProvider) {
		this.scratchpadUserDetailsService = scratchpadUserDetailsService;
		this.apiKeyAuthenticationProvider = apiKeyAuthenticationProvider;
	}

	@Override
	protected void configure(final AuthenticationManagerBuilder auth) {
		auth
			.authenticationProvider(authenticationProvider())
			.authenticationProvider(apiKeyAuthenticationProvider);
	}

	@Override
	protected void configure(final HttpSecurity httpSecurity) throws Exception {
		httpSecurity
			.httpBasic() // use basic authentication
			.and()
			.authorizeRequests()
				.anyRequest().authenticated() // every endpoint requires authorization
			.and()
				.csrf().disable();
	}

	@Override
	public void configure(final WebSecurity webSecurity) {
		webSecurity
			.ignoring()
				.antMatchers("/admin/**"); // open up user management to the world so we can create users without any authentication (normally a bad idea, but hey, this is an experiment)
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		final DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(scratchpadUserDetailsService);
		return authProvider;
	}
}
