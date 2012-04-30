package org.bk.lmt.web;

import javax.validation.Valid;

import org.bk.lmt.domain.Task;
import org.bk.lmt.service.ContextService;
import org.bk.lmt.service.GtdProjectService;
import org.bk.lmt.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/tasks")
@Controller
public class TaskController {
	@Autowired private TaskService taskService;
	@Autowired private GtdProjectService projectService;
	@Autowired private ContextService contextService;
	
	@RequestMapping(produces="text/html")
	public String list(@RequestParam(defaultValue="1", value="page", required=false) Integer page, @RequestParam(defaultValue="10", value="size", required=false)Integer size, Model model){
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userName = ((User)principal).getUsername();
		int firstResult = (page==null)?0:(page-1) * size;
		model.addAttribute("tasks",this.taskService.findTasksByUser(userName, firstResult, size));
		float nrOfPages = (float)this.taskService.countTasksByUser(userName)/size;
		int maxPages = (int)( ((nrOfPages>(int)nrOfPages) || nrOfPages==0.0)?nrOfPages+1:nrOfPages);
		model.addAttribute("maxPages", maxPages);
		return "tasks/list";
	}
	
	@RequestMapping(method=RequestMethod.POST, produces="text/html")
	public String create(@Valid Task task, BindingResult bindingResult, Model model){
    	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	String userName = ((User)principal).getUsername();
		if (bindingResult.hasErrors()){
			populateEditForm(model, task, userName);
			return "tasks/create";
		}
		model.asMap().clear();
		this.taskService.persistForUser(task, userName);
		return "redirect:/tasks";
	}
	
	@RequestMapping(params="form", produces="text/html")
	public String createForm(Model model){
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userName = ((User)principal).getUsername();
		
		populateEditForm(model, new Task(), userName);
		return "tasks/create";
	}

	private void populateEditForm(Model model, Task task, String userName){
		model.addAttribute("task", task);
		model.addAttribute("projects", this.projectService.findGTDProjectsByGtdUser(userName));
		model.addAttribute("contexts", this.contextService.findContextsByGtdUserName(userName));
	}
}
