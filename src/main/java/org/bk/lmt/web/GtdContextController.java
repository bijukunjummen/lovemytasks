package org.bk.lmt.web;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.bk.lmt.domain.GtdContext;
import org.bk.lmt.service.GtdContextService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@RequestMapping("/contexts")
@Controller
public class GtdContextController {
	
	@Resource private GtdContextService gtdContextService;	
	
	@RequestMapping(produces="text/html")
	public String list(@RequestParam(defaultValue="1",value="page", required=false) Integer page, @RequestParam(defaultValue="10", value="size", required=false) Integer size, Model model){
    	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	String userName = ((User)principal).getUsername();
    	model.addAttribute("contexts", this.gtdContextService.findContextsByGtdUserName(userName, page == null ? 0 : (page.intValue() - 1) * size, size));
        float nrOfPages = this.gtdContextService.countContextsByUserName(userName)/size;
        model.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
    	
		return "contexts/list";
	}

	@RequestMapping(method=RequestMethod.POST, produces="text/html")
	public String create(@Valid GtdContext context, BindingResult bindingResult, Model model){
    	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	String userName = ((User)principal).getUsername();
		
		if (bindingResult.hasErrors()){
			populateEditForm(model, context);
			return "contexts/create";
		}
		model.asMap().clear();
		this.gtdContextService.persistForUser(context, userName);
		return "redirect:/students/list";
	}

	@RequestMapping(params="form", produces="text/html")
	public String createForm(Model model){
		populateEditForm(model, new GtdContext());
		return "contexts/create";
	}
	
	private void populateEditForm(Model model, GtdContext context){
		model.addAttribute("context", context);
	}
}
