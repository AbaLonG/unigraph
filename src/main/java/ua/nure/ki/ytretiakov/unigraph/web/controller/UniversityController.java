package ua.nure.ki.ytretiakov.unigraph.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ua.nure.ki.ytretiakov.unigraph.data.model.Employee;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/university")
public class UniversityController {

    @GetMapping
    public ModelAndView showPage(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("university");
        Object userAttribute = request.getSession().getAttribute("user");
        if (userAttribute != null) {
            Employee employee = (Employee) userAttribute;
            modelAndView.addObject("user", employee);
        }
        return modelAndView;
    }

    @GetMapping("/structure")
    public ModelAndView structurePage(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("structure");
        return modelAndView;
    }
}
