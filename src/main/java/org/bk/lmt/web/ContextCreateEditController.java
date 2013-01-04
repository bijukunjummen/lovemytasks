package org.bk.lmt.web;

import javax.validation.Valid;

import org.bk.lmt.domain.Context;
import org.bk.lmt.repository.ContextRepository;
import org.bk.lmt.repository.TaskUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@RequestMapping("/contexts")
@Controller
public class ContextCreateEditController extends BaseController{
	
	@Autowired private ContextRepository contextRepository;
	@Autowired private TaskUserRepository taskUserRepository;
	
	@Override
	public String getPageName(){
		return "contexts";
	}
	
	@ModelAttribute
	public void contextModelAttribute(@RequestParam(value="id", required=false) Long id, Model model){
		if (id!=null){
			model.addAttribute("context", this.contextRepository.findOne(id));
			return;
		}
		
		Context context = new Context();
		context.setTaskUser(this.taskUserRepository.findUserByUserName(getUserDetails().getUsername()));
		model.addAttribute("context", context);
	}
	
	@RequestMapping(method=RequestMethod.POST, produces="text/html")
	public String create(@ModelAttribute("context") @Valid Context context, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes){
		if (bindingResult.hasErrors()){
			return "contexts/create";
		}
		model.asMap().clear();
		this.contextRepository.save(context);
		return "redirect:/contexts";
	}

	@RequestMapping(params="form", produces="text/html")
	public String createForm(Model model){
		return "contexts/create";
	}
	
	@RequestMapping(method=RequestMethod.PUT, produces="text/html")
	public String update(@ModelAttribute("context") @Valid Context context, BindingResult bindingResult, Model model){
		if (bindingResult.hasErrors()){
			return "contexts/update";
		}
		model.asMap().clear();
		this.contextRepository.save(context);
		return "redirect:/contexts";
	}
	
	@RequestMapping(params="form, id", produces="text/html")
	public String updateForm(@RequestParam("id") Long id, Model model){
		return "contexts/update";
	}
	
	
}
