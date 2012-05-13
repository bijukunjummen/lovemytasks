package org.bk.lmt.web;

import javax.annotation.Resource;

import org.bk.lmt.service.ProjectService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/projects")
@Controller
public class ProjectController {
	@Resource private ProjectService projectService;
	

	@RequestMapping(produces="text/html")
	public String list(@RequestParam(defaultValue="1", value="page", required=false) Integer page, @RequestParam(defaultValue="10", value="size", required=false) Integer size, Model model){
    	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	String userName = ((User)principal).getUsername();
    	int firstResult = (page==null)?0:(page.intValue()-1)*size;
    	model.addAttribute("projects", this.projectService.findProjectsByGtdUser(userName, firstResult, size));
    	float nrOfPages = (float)this.projectService.countProjectsByUserName(userName)/size;
        model.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));

    	return "projects/list";
	}

 }
