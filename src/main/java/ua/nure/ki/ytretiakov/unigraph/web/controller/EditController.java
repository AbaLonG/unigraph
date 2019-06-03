package ua.nure.ki.ytretiakov.unigraph.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.joda.DateTimeParser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.nure.ki.ytretiakov.unigraph.data.model.Employee;
import ua.nure.ki.ytretiakov.unigraph.data.model.enumeration.GenderType;
import ua.nure.ki.ytretiakov.unigraph.data.service.UnigraphService;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

@Controller
@RequestMapping("/edit")
public class EditController {

    private UnigraphService service;

    @Autowired
    public EditController(UnigraphService service) {
        this.service = service;
    }

    @GetMapping
    public String showUserEditPage(HttpServletRequest request) {
        Object userAttribute = request.getSession().getAttribute("user");
        if (userAttribute != null) {
            Employee user = (Employee) userAttribute;
            if (service.getEmployeeService().existsById(user.getLogin())) {
                user = service.getEmployeeService().findById(user.getLogin());
                request.getSession().setAttribute("user", user);
                return "redirect:/edit?id=" + user.getLogin();
            }
        }
        return "redirect:/login";
    }

    @GetMapping(params = "id")
    public ModelAndView showEditPage(@RequestParam String id) {
        ModelAndView modelAndView = new ModelAndView();
        if (service.getEmployeeService().existsById(id)) {
            modelAndView.addObject("user", service.getEmployeeService().findById(id));
            modelAndView.addObject("service", service);
            modelAndView.setViewName("edit");
            return modelAndView;
        }
        modelAndView.setViewName("redirect:/login");
        return modelAndView;
    }

    @PostMapping
    public String editProfile(HttpServletRequest request) {
        Object userAttribute = request.getSession().getAttribute("user");
        if (userAttribute == null) {
            return "redirect:/login";
        }
        Date date = null;
        try {
            date = new Date(new DateFormatter("yyyy-MM-dd").parse(request.getParameter("dateOfBirth"), Locale.getDefault()).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            date = new Date();
        }
        Employee user = (Employee) userAttribute;
        Employee employee = new Employee(request.getParameter("login"), request.getParameter("email"),
                request.getParameter("password"), request.getParameter("firstName"), request.getParameter("lastName"),
                date, user.getType(), GenderType.valueOf(request.getParameter("gender")));
        if (service.getEmployeeService().existsById(employee.getLogin())) {
            if (!user.getLogin().equals(employee.getLogin())) {
                return "redirect:/edit";
            }
        }
        if (service.getEmployeeService().existsByEmail(employee.getEmail())) {
            if (!user.getEmail().equals(employee.getEmail())) {
                return "redirect:/edit";
            }
        }
        service.getEmployeeService().save(employee);
        request.getSession().setAttribute("user", employee);
        return "redirect:/edit";
    }
}