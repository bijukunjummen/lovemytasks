package org.bk.lmt.web;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.bk.lmt.domain.Context;
import org.bk.lmt.service.ContextService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@RequestMapping("/contexts")
@Controller
public class ContextController {
	
	@Resource private ContextService gtdContextService;	
	
	@RequestMapping(produces="text/html")
	public String list(@RequestParam(defaultValue="1",value="page", required=false) Integer page, @RequestParam(defaultValue="10", value="size", required=false) Integer size, Model model){
    	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	String userName = ((User)principal).getUsername();
    	model.addAttribute("contexts", this.gtdContextService.findContextsByGtdUserName(userName, page == null ? 0 : (page.intValue() - 1) * size, size));
        float nrOfPages = (float)this.gtdContextService.countContextsByUserName(userName)/size;
        model.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
    	
		return "contexts/list";
	}
	
	@RequestMapping(method=RequestMethod.POST, produces="text/html")
	public String create(@Valid Context context, BindingResult bindingResult, Model model){
    	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	String userName = ((User)principal).getUsername();
		
		if (bindingResult.hasErrors()){
			populateEditForm(model, context);
			return "contexts/create";
		}
		model.asMap().clear();
		this.gtdContextService.persistForUser(context, userName);
		return "redirect:/contexts";
	}

	@RequestMapping(params="form", produces="text/html")
	public String createForm(Model model){
		populateEditForm(model, new Context());
		return "contexts/create";
	}
	
	@RequestMapping(method=RequestMethod.PUT, produces="text/html")
	public String update(@Valid Context context, BindingResult bindingResult, Model model){
    	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	String userName = ((User)principal).getUsername();
		
		if (bindingResult.hasErrors()){
			populateEditForm(model, context);
			return "contexts/update";
		}
		model.asMap().clear();
		this.gtdContextService.updateForUser(context, userName);
		return "redirect:/contexts";
	}
	
	@RequestMapping(value="/{id}", params="form", produces="text/html")
	public String updateForm(@PathVariable("id") Long id, Model model){
    	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	String userName = ((User)principal).getUsername();
		populateEditForm(model, this.gtdContextService.findById(id));
		return "contexts/update";
	}
	
	private void populateEditForm(Model model, Context context){
		model.addAttribute("context", context);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
	public String delete(@PathVariable("id") Long id,  @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model model){
    	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	String userName = ((User)principal).getUsername();
    	Context context = this.gtdContextService.findById(id);
		this.gtdContextService.remove(context);
        model.asMap().clear();
        model.addAttribute("page", (page == null) ? "1" : page.toString());
        model.addAttribute("size", (size == null) ? "10" : size.toString());
		return "redirect:/contexts";
	}
}