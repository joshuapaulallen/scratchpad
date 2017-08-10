package org.yourotherleft.scratchpad.entity;

import com.google.common.base.Objects;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Users")
public class User implements Serializable {

	@Id
	@GeneratedValue
	private Integer id;

	@Column(nullable = false, unique = true)
	private String username;

	@Column(nullable = false)
	private String password;

	public User(final String username, final String password) {
		this.username = username;
		this.password = password;
	}

	/**
	 * Zero-argument constructor needed by the JPA spec.
	 */
	protected User() {
		// nothing to do
	}

	public Integer getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		User user = (User) o;
		return Objects.equal(id, user.id) &&
			Objects.equal(username, user.username) &&
			Objects.equal(password, user.password);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id, username, password);
	}

	@Override
	public String toString() {
		return "User{" +
			"id=" + id +
			", username='" + username + '\'' +
			", password='" + password + '\'' +
			'}';
	}
}
