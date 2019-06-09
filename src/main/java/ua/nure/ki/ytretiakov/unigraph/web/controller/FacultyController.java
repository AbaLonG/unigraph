package ua.nure.ki.ytretiakov.unigraph.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.nure.ki.ytretiakov.unigraph.data.model.Cathedra;
import ua.nure.ki.ytretiakov.unigraph.data.model.Employee;
import ua.nure.ki.ytretiakov.unigraph.data.model.Faculty;
import ua.nure.ki.ytretiakov.unigraph.data.service.UnigraphService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/faculty")
public class FacultyController {
    
    private UnigraphService service;
    private IndexController indexController;
    private Faculty faculty;
    
    @Autowired
    public FacultyController(UnigraphService service, IndexController indexController) {
        this.service = service;
        this.indexController = indexController;
    }
    
    @GetMapping(params = "facultyTitle")
    public ModelAndView showPage(@RequestParam String facultyTitle, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        this.faculty = service.getFacultyService().findById(facultyTitle);
        modelAndView.setViewName("faculty");
        modelAndView.addObject("faculty", this.faculty);
        modelAndView.addObject("user", request.getSession().getAttribute("user"));
        modelAndView.addObject("controller", this);
        
        return modelAndView;
    }
    
    public String getManagerAvatar() {
        return indexController.getAvatarForEmployee(this.faculty.getFacultyManager());
    }
    
    public List<Employee> getTeachersOfFaculty() {
        return service.getEmployeeService().findAllTeachers()
                .stream().filter(e -> {
                    Cathedra c = e.getCathedra();
                    if (c != null && c.getFaculty() != null) {
                        return faculty.getTitle().equalsIgnoreCase(c.getFaculty().getTitle());
                    }
                    return false;
                })
                .collect(Collectors.toList());
    }
}
