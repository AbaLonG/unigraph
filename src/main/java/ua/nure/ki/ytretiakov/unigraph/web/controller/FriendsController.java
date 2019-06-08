package ua.nure.ki.ytretiakov.unigraph.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.nure.ki.ytretiakov.unigraph.data.model.Employee;
import ua.nure.ki.ytretiakov.unigraph.data.service.UnigraphService;
import ua.nure.ki.ytretiakov.unigraph.util.EmployeesUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/friends")
public class FriendsController {
    
    private UnigraphService unigraphService;
    private EmployeesUtil employeesUtil;
    
    @Autowired
    public FriendsController(UnigraphService unigraphService, EmployeesUtil employeesFilter) {
        this.unigraphService = unigraphService;
        this.employeesUtil = employeesFilter;
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
    public ModelAndView showPage(@RequestParam String id, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        if (!unigraphService.getEmployeeService().existsById(id)) {
            modelAndView.setViewName("redirect:/index");
        } else {
            Employee user = unigraphService.getEmployeeService().findById(id);
            modelAndView.setViewName("friends");
            modelAndView.addObject("employee", user);
            modelAndView.addObject("controller", this);
            modelAndView.addObject("faculties", unigraphService.getFacultyService().findAll());
            modelAndView.addObject("cathedras", unigraphService.getCathedraService().findAll());
            modelAndView.addObject("groups", unigraphService.getGroupService().findAll());
            Object filteredFriends = request.getSession().getAttribute("filteredFriends");
            modelAndView.addObject("filteredFriends", filteredFriends == null ? user.getFriends() : (List<Employee>) filteredFriends);
            request.getSession().removeAttribute("filteredFriends");
        }
        return modelAndView;
    }
    
    @PostMapping(value = "/filter", params = "id")
    public String filterFriends(@RequestParam String id, HttpServletRequest request) {
        List<Employee> filteredFriends = null;
        if (unigraphService.getEmployeeService().existsById(id)) {
            Employee user = unigraphService.getEmployeeService().findById(id);
            filteredFriends = user.getFriends();
            employeesUtil.filterEmployees(request, filteredFriends);
        }
        request.getSession().setAttribute("filteredFriends", filteredFriends);
        return "redirect:/friends?id=" + id;
    }
    
    public EmployeesUtil getEmployeesUtil() {
        return employeesUtil;
    }
}
