package org.bk.lmt.web;

import org.bk.lmt.domain.TaskUser;
import org.bk.lmt.types.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

public abstract class BaseController {
	@ModelAttribute
	public void fromPage(Model model){
		model.addAttribute("feature", getPageName());
	}
	
	protected CustomUserDetails getUserDetails() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return (CustomUserDetails)authentication.getPrincipal();
	}
	
	@ModelAttribute("principalname")
	public String principalName(){
		CustomUserDetails userDetails = this.getUserDetails();
		TaskUser gtdUser = userDetails.getUser();
		return gtdUser.getFullname();
	}

	protected abstract String getPageName();
	
}
