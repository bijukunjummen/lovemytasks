package org.bk.lmt.types;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.bk.lmt.domain.Authority;
import org.bk.lmt.domain.TaskUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class CustomUserDetails extends User{


	private static final long serialVersionUID = 1L;
	private final TaskUser user;
	
	public CustomUserDetails(TaskUser user) {
		super(user.getUsername(), user.getPassword(), mapAuthorities(user.getAuthorities()));
		this.user = user;
	}
	
	public TaskUser getUser() {
		return user;
	}

	private static List<GrantedAuthority> mapAuthorities(Set<Authority> gtdAuthorities){
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		if (gtdAuthorities!=null){
			for (Authority gtdAuthority: gtdAuthorities){
				authorities.add(new SimpleGrantedAuthority(gtdAuthority.getName().toString()));
			}
		}
		return authorities;
	}

}
