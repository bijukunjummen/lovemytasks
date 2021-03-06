package org.bk.lmt.security;

import javax.annotation.Resource;

import org.bk.lmt.dao.TaskUserDao;
import org.bk.lmt.domain.TaskUser;
import org.bk.lmt.types.CustomUserDetails;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

public class GtdAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

	@Resource private TaskUserDao gtdUserDao;
	
	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication)
	        throws AuthenticationException {
	}

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		TaskUser user = this.gtdUserDao.findUserByUserName(username);
		if (user!=null){
			String expectedPassword = user.getPassword();
			String inputPassword  = (String)authentication.getCredentials();
			if (expectedPassword.equals(inputPassword)){
				return new CustomUserDetails(user);
			}
		}
		
		throw new BadCredentialsException("Invalid User");
	}

	public void setGtdUserDao(TaskUserDao gtdUserDao) {
    	this.gtdUserDao = gtdUserDao;
    }

}
