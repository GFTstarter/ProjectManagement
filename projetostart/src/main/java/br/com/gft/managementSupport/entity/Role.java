package br.com.gft.managementSupport.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.codehaus.jackson.map.annotate.JsonView;

import br.com.gft.managementSupport.JsonViews;

@Entity
@SuppressWarnings("serial")
public class Role implements Serializable {

	@Id
	@GeneratedValue
	@Column (name = "id_role")
	private Long id;

	@Column
	private String authority;

	public Role() {
	}

	@JsonView(JsonViews.Admin.class)
	public Long getId() {

		return this.id;
	}

	@JsonView(JsonViews.User.class)
	public String getAuthority() {
		return this.authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	@Override
	public String toString() {
		return String.format("Role[%s]", this.authority);
	}

}
