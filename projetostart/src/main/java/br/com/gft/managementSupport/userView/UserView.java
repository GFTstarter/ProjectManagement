package br.com.gft.managementSupport.userView;

import java.util.Map;

public class UserView {

	private final Long id;
	
	private final String name;

	private final Map<String, Boolean> roles;

	private final String token;

	public UserView(Long id, String userName, Map<String, Boolean> roles, String token) {
		this.id = id;
		this.name = userName;
		this.roles = roles;
		this.token = token;
	}

	public Long getId() {
		return id;
	}
	
	public String getName() {
		return this.name;
	}


	public Map<String, Boolean> getRoles() {
		return this.roles;
	}


	public String getToken() {
		return this.token;
	}

}