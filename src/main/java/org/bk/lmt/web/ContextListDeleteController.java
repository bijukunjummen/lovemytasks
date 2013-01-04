package org.bk.lmt.web;

import org.bk.lmt.domain.Context;
import org.bk.lmt.repository.ContextRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@RequestMapping("/contexts")
@Controller
public class ContextListDeleteController extends BaseController{
	
	@Autowired private ContextRepository contextRepository;
	
	@Override
	public String getPageName(){
		return "contexts";
	}
	
	@RequestMapping(produces="text/html")
	public String list(@RequestParam(defaultValue="1",value="page", required=false) Integer page, @RequestParam(defaultValue="10", value="size", required=false) Integer size, Model model){
    	String userName = getUserDetails().getUsername();
    	Page<Context> pageList = this.contextRepository.findByUser(userName, new PageRequest(page-1, size));
    	model.addAttribute("contexts", pageList.getContent());
        int nrOfPages = pageList.getTotalPages();
        model.addAttribute("maxPages", nrOfPages);
    	
		return "contexts/list";
	}
	
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
	public String delete(@PathVariable("id") Long id,  @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model model){
//    	String userName = getUserDetails().getUsername();
    	Context context = this.contextRepository.findOne(id);
		this.contextRepository.delete(context);
        model.asMap().clear();
        model.addAttribute("page", (page == null) ? "1" : page.toString());
        model.addAttribute("size", (size == null) ? "10" : size.toString());
		return "redirect:/contexts";
	}	
}
