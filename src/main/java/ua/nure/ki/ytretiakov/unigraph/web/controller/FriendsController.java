package ua.nure.ki.ytretiakov.unigraph.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.nure.ki.ytretiakov.unigraph.data.model.Employee;
import ua.nure.ki.ytretiakov.unigraph.data.service.UnigraphService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/friends")
public class FriendsController {

    private UnigraphService unigraphService;
    private IndexController indexController;

    @Autowired
    public FriendsController(UnigraphService unigraphService, IndexController indexController) {
        this.unigraphService = unigraphService;
        this.indexController = indexController;
    }

    @GetMapping
    public ModelAndView userFriends(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        Object userAttribute = request.getSession().getAttribute("user");
        if (userAttribute != null) {
            Employee user = (Employee) userAttribute;
            if (unigraphService.getEmployeeService().existsById(user.getLogin())) {
                user = unigraphService.getEmployeeService().findById(user.getLogin());
                request.getSession().setAttribute("user", user);
                modelAndView.setViewName("redirect:/friends?id=" + user.getLogin());
                return modelAndView;
            } else {
                request.getSession().removeAttribute("user");
            }
        }
        modelAndView.setViewName("redirect:/login");
        return modelAndView;
    }

    @GetMapping(params = "id")
    public ModelAndView showPage(@RequestParam String id) {
        ModelAndView modelAndView = new ModelAndView();
        if (unigraphService.getEmployeeService().existsById(id)) {
            Employee user = unigraphService.getEmployeeService().findById(id);
            modelAndView.setViewName("friends");
            modelAndView.addObject("employee", user);
            modelAndView.addObject("controller", this);
            modelAndView.addObject("faculties", unigraphService.getFacultyService().findAll());
            modelAndView.addObject("cathedras", unigraphService.getCathedraService().findAll());
            modelAndView.addObject("groups", unigraphService.getGroupService().findAll());
        } else {
            modelAndView.setViewName("redirect:/index");
        }
        return modelAndView;
    }

    public String getAvatarForEmployee(Employee employee) {
        return indexController.getAvatarForEmployee(employee);
    }
}
