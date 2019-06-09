package ua.nure.ki.ytretiakov.unigraph.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.nure.ki.ytretiakov.unigraph.data.model.Cathedra;
import ua.nure.ki.ytretiakov.unigraph.data.model.Employee;
import ua.nure.ki.ytretiakov.unigraph.data.model.Faculty;
import ua.nure.ki.ytretiakov.unigraph.data.model.Group;
import ua.nure.ki.ytretiakov.unigraph.data.model.enumeration.EmployeeType;
import ua.nure.ki.ytretiakov.unigraph.data.service.UnigraphService;
import ua.nure.ki.ytretiakov.unigraph.util.EmployeesUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/university")
public class UniversityController {
    
    private static final int DEFAULT_EMPLOYEES_COUNT = 20;
    
    private UnigraphService service;
    private EmployeesUtil employeesUtil;
    
    @Autowired
    public UniversityController(UnigraphService service, EmployeesUtil employeesUtil) {
        this.service = service;
        this.employeesUtil = employeesUtil;
    }
    
    @GetMapping
    public ModelAndView showPage(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("university");
        Object userAttribute = request.getSession().getAttribute("user");
        if (userAttribute != null) {
            Employee employee = (Employee) userAttribute;
            modelAndView.addObject("user", employee);
            modelAndView.addObject("employeesUtil", employeesUtil);
            modelAndView.addObject("service", service);
            final Object filteredEmployees = request.getSession().getAttribute("filteredEmployees");
            if (filteredEmployees != null) {
                modelAndView.addObject("filteredEmployees", (List<Employee>) filteredEmployees);
            } else {
                modelAndView.addObject("filteredEmployees", service.getEmployeeService().findAllWithCount(DEFAULT_EMPLOYEES_COUNT));
            }
            request.getSession().removeAttribute("filteredEmployees");
        }
        return modelAndView;
    }
    
    @GetMapping("/structure")
    public ModelAndView structurePage(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("structure");
        Object userAttribute = request.getSession().getAttribute("user");
        if (userAttribute == null || ((Employee) userAttribute).getType() != EmployeeType.Teacher) {
            return new ModelAndView("redirect:/university");
        }
        modelAndView.addObject("service", service);
        return modelAndView;
    }
    
    @PostMapping(value = "/filter")
    public String filterFriends(@RequestParam(required = false) Integer count, HttpServletRequest request) {
        List<Employee> filteredEmployees = service.getEmployeeService().findAll();
        employeesUtil.filterEmployees(request, filteredEmployees);
        filteredEmployees = filteredEmployees.stream().limit(count == null ? DEFAULT_EMPLOYEES_COUNT : count).collect(Collectors.toList());
        request.getSession().setAttribute("filteredEmployees", filteredEmployees);
        return "redirect:/university";
    }
    
    @PostMapping("/structure/addFaculty")
    public String addFaculty(HttpServletRequest request) {
        Faculty faculty = new Faculty();
        faculty.setTitle(request.getParameter("title"));
        final Employee facultyManager = service.getEmployeeService().findById(request.getParameter("manager"));
        faculty.setFacultyManager(facultyManager);
        service.getFacultyService().save(faculty);
        return "redirect:/university/structure";
    }
    
    @PostMapping("/structure/addCathedra")
    public String addCathedra(HttpServletRequest request) {
        Cathedra cathedra = new Cathedra();
        cathedra.setTitle(request.getParameter("title"));
        cathedra.setFaculty(service.getFacultyService().findById(request.getParameter("faculty")));
        final Employee cathedraManager = service.getEmployeeService().findById(request.getParameter("manager"));
        cathedraManager.setCathedra(cathedra);
        cathedra.setCathedraManager(cathedraManager);
        service.getCathedraService().save(cathedra);
        service.getEmployeeService().save(cathedraManager);
        return "redirect:/university/structure";
    }
    
    @PostMapping("/structure/addGroup")
    public String addGroup(HttpServletRequest request) {
        Group group = new Group();
        group.setTitle(request.getParameter("title"));
        final Cathedra cathedra = service.getCathedraService().findById(request.getParameter("cathedra"));
        final Employee groupManager = service.getEmployeeService().findById(request.getParameter("manager"));
        group.setCathedra(cathedra);
        groupManager.setCathedra(cathedra);
        group.setGroupManager(groupManager);
        service.getGroupService().save(group);
        return "redirect:/university/structure";
    }
    
    public EmployeesUtil getEmployeesUtil() {
        return employeesUtil;
    }
}
