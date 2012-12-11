package org.bk.lmt.web;

import java.text.SimpleDateFormat;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.bk.lmt.domain.Project;
import org.bk.lmt.service.ProjectService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/projects")
@Controller
public class ProjectController extends BaseController{
	
	@Override
	public String getPageName(){
		return "projects";
	}
	@Resource private ProjectService projectService;
	

	@RequestMapping(produces="text/html")
	public String list(@RequestParam(defaultValue="1", value="page", required=false) Integer page, @RequestParam(defaultValue="10", value="size", required=false) Integer size, Model model){
    	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	String userName = ((UserDetails)principal).getUsername();
    	int firstResult = (page==null)?0:(page.intValue()-1)*size;
    	model.addAttribute("projects", this.projectService.findProjectsByGtdUser(userName, firstResult, size));
    	float nrOfPages = (float)this.projectService.countProjectsByUserName(userName)/size;
        model.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));

    	return "projects/list";
	}
	
	@RequestMapping(params="form", produces="text/html")
	public String createForm(Model model){
		populateEditForm(model, new Project());
		return "projects/create";
	} 
	
	@RequestMapping(method=RequestMethod.POST, produces="text/html")
	public String create(@Valid Project project, BindingResult bindingResult, Model model){
    	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	String userName = ((UserDetails)principal).getUsername();
		
		if (bindingResult.hasErrors()){
			populateEditForm(model, project);
			return "projects/create";
		}
		
		model.asMap().clear();
		this.projectService.persistForUser(project, userName);
		return "redirect:/projects";
	}

	@RequestMapping(method=RequestMethod.PUT, produces="text/html")
	public String update(@Valid Project project, BindingResult bindingResult, Model model){
    	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	String userName = ((UserDetails)principal).getUsername();
		if (bindingResult.hasErrors()){
			populateEditForm(model, project);
			return "projects/update";
		}
		model.asMap().clear();
		this.projectService.updateForUser(project, userName);
		return "redirect:/projects";
	}
	
	@RequestMapping(value="/{id}", params="form", produces="text/html")
	public String updateForm(@PathVariable("id") Long id, Model model){
		populateEditForm(model, this.projectService.findById(id));
		return "projects/update";
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE, produces="text/html")
	public String delete(@PathVariable("id") Long id, @RequestParam(defaultValue="1", value="page", required=false) Integer page, 
				@RequestParam(defaultValue="10", value="size", required=false) Integer size, Model model){
        this.projectService.remove(this.projectService.findById(id));
		model.asMap().clear();
        model.addAttribute("page", (page == null) ? "1" : page.toString());
        model.addAttribute("size", (size == null) ? "10" : size.toString());
		return "redirect:/projects";
	}
	
	private void populateEditForm(Model model, Project project){
		model.addAttribute("project", project);
	}
	
//	@InitBinder
//    public void initBinder(WebDataBinder binder) {
//		System.out.println("======"+binder.getObjectName());
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        dateFormat.setLenient(false);
//        System.out.println("======"+binder.getObjectName());
////        binder.
////        binder.regi
//    }
 }
