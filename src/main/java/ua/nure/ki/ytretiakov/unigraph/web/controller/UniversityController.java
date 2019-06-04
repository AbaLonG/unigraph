package ua.nure.ki.ytretiakov.unigraph.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ua.nure.ki.ytretiakov.unigraph.data.model.Cathedra;
import ua.nure.ki.ytretiakov.unigraph.data.model.Employee;
import ua.nure.ki.ytretiakov.unigraph.data.model.Faculty;
import ua.nure.ki.ytretiakov.unigraph.data.model.Group;
import ua.nure.ki.ytretiakov.unigraph.data.service.UnigraphService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/university")
public class UniversityController {
    
    private UnigraphService service;
    
    @Autowired
    public UniversityController(UnigraphService service) {
        this.service = service;
    }
    
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
        modelAndView.addObject("service", service);
        return modelAndView;
    }
    
    @PostMapping("/structure/addFaculty")
    public String addFaculty(HttpServletRequest request) {
        Faculty faculty = new Faculty();
        faculty.setTitle(request.getParameter("title"));
        faculty.setFacultyManager(service.getEmployeeService().findById(request.getParameter("manager")));
        service.getFacultyService().save(faculty);
        return "redirect:/university/structure";
    }
    
    @PostMapping("/structure/addCathedra")
    public String addCathedra(HttpServletRequest request) {
        Cathedra cathedra = new Cathedra();
        cathedra.setTitle(request.getParameter("title"));
        cathedra.setFaculty(service.getFacultyService().findById(request.getParameter("faculty")));
        cathedra.setCathedraManager(service.getEmployeeService().findById(request.getParameter("manager")));
        service.getCathedraService().save(cathedra);
        return "redirect:/university/structure";
    }
    
    @PostMapping("/structure/addGroup")
    public String addGroup(HttpServletRequest request) {
        Group group = new Group();
        group.setTitle(request.getParameter("title"));
        group.setCathedra(service.getCathedraService().findById(request.getParameter("cathedra")));
        group.setGroupManager(service.getEmployeeService().findById(request.getParameter("manager")));
        service.getGroupService().save(group);
        return "redirect:/university/structure";
    }
}
