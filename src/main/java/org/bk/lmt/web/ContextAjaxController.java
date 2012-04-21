package org.bk.lmt.web;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.bk.lmt.domain.Context;
import org.bk.lmt.service.ContextService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

@RequestMapping("/ajaxContexts")
@Controller
public class ContextAjaxController {
	@Resource private ContextService gtdContextService;
	

    @RequestMapping(value="read.json", method = RequestMethod.GET)
    public @ResponseBody Map<String, ? extends Object> jsonlist(@RequestParam(value = "start", required = false) Integer start, @RequestParam(value = "limit", required = false) Integer limit, @RequestParam(value = "page", required = false, defaultValue="1") Integer page) {
    	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	String userName = ((User)principal).getUsername();    	
		Map<String, Object> modelMap = new HashMap<String, Object>();
    	
    	int sizeNo = limit == null ? 10 : limit.intValue();
    	modelMap.put("data", this.gtdContextService.findContextsByGtdUserName(userName, page == null ? 0 : (page.intValue() - 1) * sizeNo, sizeNo));
    	Long totalSize =  this.gtdContextService.countContextsByUserName(userName);
    	modelMap.put("totalSize", totalSize);
		return modelMap;
    }

    @RequestMapping(value="create.json", method = RequestMethod.POST)
    public ModelAndView jsoncreate(@RequestBody Context gtdContext) {
    	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	String userName = ((User)principal).getUsername();    	
		Map<String, Object> modelMap = new HashMap<String, Object>();
		Context gtdContextUpdated = this.gtdContextService.persistForUser(gtdContext, userName);
    	modelMap.put("success", "true");
    	modelMap.put("data", Collections.singletonList(gtdContextUpdated));
    	MappingJacksonJsonView mappingJacksonView = new MappingJacksonJsonView();
		ModelAndView modelAndView = new ModelAndView(mappingJacksonView, modelMap);
		return modelAndView;
    }

    @RequestMapping(value="update.json", method = RequestMethod.POST)
    public ModelAndView jsonupdate(@RequestBody Context gtdContext) {
    	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	String userName = ((User)principal).getUsername();    	
		Map<String, Object> modelMap = new HashMap<String, Object>();
		Context gtdContextUpdated = this.gtdContextService.updateForUser(gtdContext, userName);
    	modelMap.put("success", "true");
    	modelMap.put("data", Collections.singletonList(gtdContextUpdated));
    	MappingJacksonJsonView mappingJacksonView = new MappingJacksonJsonView();
		ModelAndView modelAndView = new ModelAndView(mappingJacksonView, modelMap);
		return modelAndView;
    	
    }

    @RequestMapping(value="delete.json", method = RequestMethod.POST)
    public ModelAndView jsondelete(@RequestBody Context gtdContext) {
//    	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//    	String userName = ((User)principal).getUsername();    	
		Map<String, Object> modelMap = new HashMap<String, Object>();
		this.gtdContextService.remove(this.gtdContextService.findById(gtdContext.getId()));
    	modelMap.put("success", "true");
    	MappingJacksonJsonView mappingJacksonView = new MappingJacksonJsonView();
		ModelAndView modelAndView = new ModelAndView(mappingJacksonView, modelMap);
		return modelAndView;
    	
    }
}
