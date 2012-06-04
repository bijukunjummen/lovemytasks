package org.bk.lmt.web;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

abstract class BaseController {
	@ModelAttribute
	public void fromPage(Model model){
		model.addAttribute("feature", getPageName());
	}
	
	protected abstract String getPageName();
}
