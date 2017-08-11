package org.yourotherleft.scratchpad.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;

public class ApiKeyAuthorizationToken extends AbstractAuthenticationToken {

	private final String token;

	public ApiKeyAuthorizationToken(final String token) {
		super(null);
		this.token = token;
	}

	@Override
	public Object getCredentials() {
		return token;
	}

	@Override
	public Object getPrincipal() {
		return null;
	}
}
