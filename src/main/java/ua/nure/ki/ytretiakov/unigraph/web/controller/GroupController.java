package ua.nure.ki.ytretiakov.unigraph.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.nure.ki.ytretiakov.unigraph.data.model.Group;
import ua.nure.ki.ytretiakov.unigraph.data.service.UnigraphService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/group")
public class GroupController {
    
    private UnigraphService service;
    private IndexController indexController;
    private Group group;
    
    @Autowired
    public GroupController(UnigraphService service, IndexController indexController) {
        this.service = service;
        this.indexController = indexController;
    }
    
    @GetMapping(params = "groupTitle")
    public ModelAndView showPage(@RequestParam String groupTitle, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        group = service.getGroupService().findById(groupTitle);
        modelAndView.setViewName("group");
        modelAndView.addObject("group", group);
        modelAndView.addObject("user", request.getSession().getAttribute("user"));
        modelAndView.addObject("controller", this);
        return modelAndView;
    }
    
    public String getManagerAvatar() {
        return indexController.getAvatarForEmployee(this.group.getGroupManager());
    }
}
