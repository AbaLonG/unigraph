package ua.nure.ki.ytretiakov.unigraph.web.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ua.nure.ki.ytretiakov.unigraph.data.model.Employee;
import ua.nure.ki.ytretiakov.unigraph.data.service.EmployeeService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final static Logger logger = Logger.getLogger(LoginController.class);

    private EmployeeService employeeService;

    @Autowired
    public LoginController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ModelAndView showPage(final HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        final Object userAttribute = request.getSession().getAttribute("user");
        if (userAttribute != null) {
            Employee user = (Employee) userAttribute;
            modelAndView.setViewName("redirect:/index");
            return modelAndView;
        }
        modelAndView.setViewName("login");
        modelAndView.addObject("employee", new Employee());
        return modelAndView;
    }

    @PostMapping
    public String login(@ModelAttribute("employee") Employee employee, HttpServletRequest request) {
        if (employeeService.existsById(employee.getLogin())) {
            Employee user = employeeService.findById(employee.getLogin());
            if (user.getPassword().equals(employee.getPassword())) {
                request.getSession().setAttribute("user", user);
                return "redirect:/index?id=" + user.getLogin();
            }
        }
        return "login";
    }
}
