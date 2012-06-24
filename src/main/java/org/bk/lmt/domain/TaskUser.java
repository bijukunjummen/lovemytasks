package org.bk.lmt.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
@Entity
@Table(name="taskusers")

@NamedQueries({
	@NamedQuery(name="TaskUser.findUserByUserName", query="select o from TaskUser o where o.username=:username"),
	@NamedQuery(name="TaskUser.findAll", query="select o from TaskUser o")
})
public class TaskUser implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Size(min = 1)
	private String fullname;

	@NotNull
	@Size(min = 1)
	private String username;

	@NotNull
	@Size(min = 1)
	private String password;

	@Version
	@Column(name = "version")
	private Integer version;
	
	@ElementCollection
	@CollectionTable(name="authorities", joinColumns=@JoinColumn(name="taskuser_id"))
	private Set<Authority> authorities= new HashSet<Authority>();

	public String getFullname() {
		return this.fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	
	public Set<Authority> getAuthorities() {
    	return authorities;
    }

	public void setAuthorities(Set<Authority> authorities) {
    	this.authorities = authorities;
    }

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}



}
