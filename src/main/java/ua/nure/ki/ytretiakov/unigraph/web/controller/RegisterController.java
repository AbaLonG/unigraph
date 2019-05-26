package ua.nure.ki.ytretiakov.unigraph.web.controller;

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
@RequestMapping("/register")
public class RegisterController {

    private EmployeeService employeeService;

    @Autowired
    public RegisterController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ModelAndView showPage() {
        return new ModelAndView("register", "employee", new Employee());
    }

    @PostMapping
    public ModelAndView registerEmployee(@ModelAttribute("employee") Employee employee, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        if (employeeService.existsById(employee.getLogin())) {
            modelAndView.setViewName("register");
            return modelAndView;
        } else {
            employeeService.save(employee);
            request.getSession().setAttribute("user", employee);
            modelAndView.setViewName("redirect:/index");
            return modelAndView;
        }
    }
}
