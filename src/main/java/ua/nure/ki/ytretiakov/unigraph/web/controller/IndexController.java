package ua.nure.ki.ytretiakov.unigraph.web.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.nure.ki.ytretiakov.unigraph.data.model.Employee;
import ua.nure.ki.ytretiakov.unigraph.data.service.EmployeeService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    private final static Logger logger = Logger.getLogger(IndexController.class);

    private EmployeeService employeeService;

    @Autowired
    public IndexController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping({"/", "/index"})
    public ModelAndView index(HttpServletRequest request) {
        Object userAttribute = request.getSession().getAttribute("user");
        if (userAttribute == null) {
            return new ModelAndView("redirect:/login");
        } else {
            Employee user = (Employee) userAttribute;
            return new ModelAndView("redirect:/index?id=" + user.getLogin());
        }
    }

    @GetMapping(value = "/index", params = "id")
    public ModelAndView showPage(@RequestParam String id, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        Object userAttribute = request.getSession().getAttribute("user");
        if (userAttribute != null) {
            Employee user = (Employee) userAttribute;
            if (user.getLogin().equals(id)) {
                modelAndView.addObject("employee", user);
                modelAndView.setViewName("index");
                return modelAndView;
            }
        }
        boolean idExists = employeeService.existsById(id);
        if (idExists) {
            modelAndView.addObject("employee", employeeService.findById(id));
            modelAndView.setViewName("index");
            return modelAndView;
        } else {
            modelAndView.setViewName("redirect:/login");
            return modelAndView;
        }
    }
}
