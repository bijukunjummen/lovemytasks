package org.bk.lmt.types;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.bk.lmt.domain.GtdAuthority;
import org.bk.lmt.domain.GtdUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class CustomUserDetails extends User{


	private static final long serialVersionUID = 1L;
	private final GtdUser user;
	
	public CustomUserDetails(GtdUser user) {
		super(user.getUsername(), user.getPassword(), mapAuthorities(user.getGtdAuthorities()));
		this.user = user;
	}
	
	public GtdUser getUser() {
		return user;
	}

	private static List<GrantedAuthority> mapAuthorities(Set<GtdAuthority> gtdAuthorities){
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		if (gtdAuthorities!=null){
			for (GtdAuthority gtdAuthority: gtdAuthorities){
				authorities.add(new SimpleGrantedAuthority(gtdAuthority.getName().toString()));
			}
		}
		return authorities;
	}

}
