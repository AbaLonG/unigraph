package ua.nure.ki.ytretiakov.unigraph.web.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.nure.ki.ytretiakov.unigraph.data.model.Employee;
import ua.nure.ki.ytretiakov.unigraph.data.service.EmployeeService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/login")
public class LoginController {

    private EmployeeService employeeService;

    @Autowired
    public LoginController(final EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    private final static Logger logger = Logger.getLogger(LoginController.class);

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView greet(final HttpServletRequest request) {
        if (request.getSession().getAttribute("employee") != null) {
            return new ModelAndView("index");
        } else {
            return new ModelAndView("login", "employee", new Employee());
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView loginEmployee(final @ModelAttribute("employee") Employee employee,
                                      final BindingResult result) {
        final ModelAndView modelAndView = new ModelAndView();
        if (!result.hasErrors()) {
            if (employeeService.existsById(employee.getEmail())) {
                modelAndView.addObject("employee", employeeService.findById(employee.getEmail()));
                modelAndView.setViewName("index");
                return modelAndView;
            }
        }
        modelAndView.setViewName("login");
        return modelAndView;
    }
}
