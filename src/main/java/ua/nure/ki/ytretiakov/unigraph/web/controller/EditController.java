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
            modelAndView.setViewName("edit");
            return modelAndView;
        }
        modelAndView.setViewName("redirect:/login");
        return modelAndView;
    }
}