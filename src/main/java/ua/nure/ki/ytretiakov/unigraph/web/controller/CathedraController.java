package ua.nure.ki.ytretiakov.unigraph.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.nure.ki.ytretiakov.unigraph.data.model.Cathedra;
import ua.nure.ki.ytretiakov.unigraph.data.model.Employee;
import ua.nure.ki.ytretiakov.unigraph.data.service.UnigraphService;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@RequestMapping("/cathedra")
public class CathedraController {
    
    private UnigraphService service;
    private IndexController indexController;
    private Cathedra cathedra;
    
    @Autowired
    public CathedraController(UnigraphService service, IndexController indexController) {
        this.service = service;
        this.indexController = indexController;
    }
    
    @GetMapping(params = "cathedraTitle")
    public ModelAndView showPage(@RequestParam String cathedraTitle, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        cathedra = service.getCathedraService().findById(cathedraTitle);
        modelAndView.setViewName("cathedra");
        modelAndView.addObject("cathedra", cathedra);
        modelAndView.addObject("user", request.getSession().getAttribute("user"));
        modelAndView.addObject("controller", this);
        return modelAndView;
    }
    
    public String getManagerAvatar() {
        return indexController.getAvatarForEmployee(this.cathedra.getCathedraManager());
    }
    
    public List<Employee> getTeachersOfCathedra() {
        try {
            List<Employee> teachers = service.getEmployeeService().findAllTeachers();
            Stream<Employee> teachersStream = teachers.stream().filter(e -> {
                Cathedra c = e.getCathedra();
                if (c != null) {
                    if (cathedra != null) {
                        return cathedra.getTitle().equalsIgnoreCase(c.getTitle());
                    }
                }
                return false;
            });
            return teachersStream.collect(Collectors.toList());
        } catch (Exception e) {
            return Collections.EMPTY_LIST;
        }
    }
}
