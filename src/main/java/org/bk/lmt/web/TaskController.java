package org.bk.lmt.web;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.bk.lmt.domain.Task;
import org.bk.lmt.service.ContextService;
import org.bk.lmt.service.ProjectService;
import org.bk.lmt.service.TaskService;
import org.bk.lmt.types.ListWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/tasks")
@Controller
public class TaskController extends BaseController{
	@Autowired private TaskService taskService;
	@Autowired private ProjectService projectService;
	@Autowired private ContextService contextService;
	
	@Override
	protected String getPageName(){
		return "tasks";
	}
	
	@RequestMapping(produces="text/html")
	public String list(@RequestParam(defaultValue="1", value="page", required=false) Integer page, 
				@RequestParam(defaultValue="10", value="size", required=false) Integer size, Principal principal, Model model){
		String userName = this.getUserDetails().getUsername();
		int firstResult = (page==null)?0:(page-1) * size;
		model.addAttribute("tasks",this.taskService.findIncompleteTasksByUser(userName, firstResult, size));
		float nrOfPages = (float)this.taskService.countIncompleteTasksByUser(userName)/size;
		int maxPages = (int)( ((nrOfPages>(int)nrOfPages) || nrOfPages==0.0)?nrOfPages+1:nrOfPages);
		model.addAttribute("maxPages", maxPages);
		return "tasks/list";
	}
	
	@RequestMapping(produces="application/json", value="/listLoad")
	public @ResponseBody ListWrapper<Task> listLoad(DatatableForm datatableForm, Principal principal, Model model){
		String userName = this.getUserDetails().getUsername();
		int firstResult = datatableForm.getiDisplayStart();
		List<Task> tasks = null;
		if (datatableForm.getsSearch()==null){
			tasks = this.taskService.findTasksByUser(userName, firstResult, datatableForm.getiDisplayLength());
		}else{
			tasks = this.taskService.findTasksByUserAndNameFilter(userName, datatableForm.getsSearch(), firstResult, datatableForm.getiDisplayLength());
		}
		long noOfRecords = this.taskService.countTasksByUser(userName);
		float nrOfPages = (float)noOfRecords/datatableForm.getiDisplayLength();
		int maxPages = (int)( ((nrOfPages>(int)nrOfPages) || nrOfPages==0.0)?nrOfPages+1:nrOfPages);
		ListWrapper<Task> taskList = new ListWrapper<Task>();
		taskList.setAaData(tasks);
		taskList.setiTotalDisplayRecords((int)noOfRecords);
		taskList.setiTotalRecords((int)noOfRecords);
		return taskList;
	}
	
	@RequestMapping(method=RequestMethod.POST, produces="text/html")
	public String create(@Valid Task task, BindingResult bindingResult, Model model){
    	String userName = this.getUserDetails().getUsername();
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
		String userName = this.getUserDetails().getUsername();
		
		populateEditForm(model, new Task(), userName);
		return "tasks/create";
	}
	
	@RequestMapping(value="/{id}", params="form", produces="text/html")
	public String updateForm(@PathVariable("id") Long id,  Model model){
    	String userName = this.getUserDetails().getUsername();
    	populateEditForm(model, this.taskService.findById(id), userName);
		return "tasks/update";
	}
	
	@RequestMapping(method=RequestMethod.PUT, produces="text/html")
	public String update(@Valid Task task, BindingResult bindingResult, Model model){
    	String userName = this.getUserDetails().getUsername();
    	if (bindingResult.hasErrors()){
    		populateEditForm(model, task, userName);
    		return "tasks/update";
    	}
    	model.asMap().clear();
    	this.taskService.updateForUser(task, userName);
		return "redirect:/tasks";
	}
	
	@RequestMapping(value="{id}", method=RequestMethod.DELETE, produces="text/html")
	public String delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model model){
//    	String userName = this.getAuthentication().getUsername();
    	Task task = this.taskService.findById(id);
    	this.taskService.remove(task);
        model.asMap().clear();
        model.addAttribute("page", (page == null) ? "1" : page.toString());
        model.addAttribute("size", (size == null) ? "10" : size.toString());
		return "redirect:/tasks";    	
	}

	private void populateEditForm(Model model, Task task, String userName){
		model.addAttribute("task", task);
		model.addAttribute("projects", this.projectService.findProjectsByGtdUser(userName));
		model.addAttribute("contexts", this.contextService.findContextsByGtdUserName(userName));
	}
}
