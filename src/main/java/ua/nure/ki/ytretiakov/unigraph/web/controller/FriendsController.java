package ua.nure.ki.ytretiakov.unigraph.web.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.nure.ki.ytretiakov.unigraph.data.model.Employee;
import ua.nure.ki.ytretiakov.unigraph.data.model.Faculty;
import ua.nure.ki.ytretiakov.unigraph.data.model.Group;
import ua.nure.ki.ytretiakov.unigraph.data.model.enumeration.EmployeeType;
import ua.nure.ki.ytretiakov.unigraph.data.service.UnigraphService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/friends")
public class FriendsController {
    
    private UnigraphService unigraphService;
    private IndexController indexController;
    
    @Autowired
    public FriendsController(UnigraphService unigraphService, IndexController indexController) {
        this.unigraphService = unigraphService;
        this.indexController = indexController;
    }
    
    @GetMapping
    public ModelAndView userFriends(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        Object userAttribute = request.getSession().getAttribute("user");
        if (userAttribute != null) {
            Employee user = (Employee) userAttribute;
            if (unigraphService.getEmployeeService().existsById(user.getLogin())) {
                user = unigraphService.getEmployeeService().findById(user.getLogin());
                request.getSession().setAttribute("user", user);
                modelAndView.setViewName("redirect:/friends?id=" + user.getLogin());
                return modelAndView;
            } else {
                request.getSession().removeAttribute("user");
            }
        }
        modelAndView.setViewName("redirect:/login");
        return modelAndView;
    }
    
    @GetMapping(params = "id")
    public ModelAndView showPage(@RequestParam String id, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        if (!unigraphService.getEmployeeService().existsById(id)) {
            modelAndView.setViewName("redirect:/index");
        } else {
            Employee user = unigraphService.getEmployeeService().findById(id);
            modelAndView.setViewName("friends");
            modelAndView.addObject("employee", user);
            modelAndView.addObject("controller", this);
            modelAndView.addObject("faculties", unigraphService.getFacultyService().findAll());
            modelAndView.addObject("cathedras", unigraphService.getCathedraService().findAll());
            modelAndView.addObject("groups", unigraphService.getGroupService().findAll());
            Object filteredFriends = request.getSession().getAttribute("filteredFriends");
            modelAndView.addObject("filteredFriends", filteredFriends == null ? user.getFriends() : (List<Employee>) filteredFriends);
            request.getSession().removeAttribute("filteredFriends");
        }
        return modelAndView;
    }
    
    public String getAvatarForEmployee(Employee employee) {
        return indexController.getAvatarForEmployee(employee);
    }
    
    @PostMapping(value = "/filter", params = "id")
    public String filterFriends(@RequestParam String id, HttpServletRequest request) {
        List<Employee> filteredFriends = null;
        if (unigraphService.getEmployeeService().existsById(id)) {
            Employee user = unigraphService.getEmployeeService().findById(id);
            filteredFriends = user.getFriends();
            filterByFaculty(filteredFriends, request.getParameter("facultyTitle"));
            filterByCathedra(filteredFriends, request.getParameter("cathedraTitle"));
            filterByGroup(filteredFriends, request.getParameter("groupTitle"));
            filterByType(filteredFriends, request.getParameter("employeeType"));
            filterByName(filteredFriends, request.getParameter("nameFilter"));
        }
        request.getSession().setAttribute("filteredFriends", filteredFriends);
        return "redirect:/friends?id=" + id;
    }
    
    private void filterByFaculty(List<Employee> employees, String facultyTitle) {
        if (facultyTitle != null && !facultyTitle.equalsIgnoreCase("any")) {
            List<Employee> facultyEmployees = new ArrayList<>();
            for (Employee e : employees) {
                if (e.getCathedra() != null) {
                    Faculty faculty = e.getCathedra().getFaculty();
                    if (faculty != null) {
                        if (faculty.getTitle().equalsIgnoreCase(facultyTitle)) {
                            facultyEmployees.add(e);
                        }
                    }
                }
            }
            employees.retainAll(facultyEmployees);
        }
    }
    
    private void filterByCathedra(List<Employee> employees, String cathedraTitle) {
        if (cathedraTitle != null && !cathedraTitle.equalsIgnoreCase("any")) {
            List<Employee> cathedraEmployees = new ArrayList<>();
            for (Employee e : employees) {
                if (e.getCathedra() != null && e.getCathedra().getTitle().equalsIgnoreCase(cathedraTitle)) {
                    cathedraEmployees.add(e);
                }
            }
            employees.retainAll(cathedraEmployees);
        }
    }
    
    private void filterByGroup(List<Employee> employees, String groupTitle) {
        if (groupTitle != null && !groupTitle.equalsIgnoreCase("any")) {
            List<Employee> groupEmployees = new ArrayList<>();
            for (Employee e : employees) {
                if (e.getType() == EmployeeType.Student) {
                    if (e.getGroup() != null && e.getGroup().getTitle().equalsIgnoreCase(groupTitle)) {
                        groupEmployees.add(e);
                    }
                } else if (e.getType() == EmployeeType.Teacher) {
                    final Group groupOfManager = unigraphService.getGroupService().findGroupOfManager(e.getLogin());
                    if (groupOfManager != null && groupOfManager.getTitle().equalsIgnoreCase(groupTitle)) {
                        groupEmployees.add(e);
                    }
                }
            }
            employees.retainAll(groupEmployees);
        }
    }
    
    private void filterByType(List<Employee> filteredFriends, String employeeType) {
        if (StringUtils.isBlank(employeeType) || "any".equalsIgnoreCase(employeeType))
            return;
        
        filteredFriends.retainAll(filteredFriends.stream()
                .filter(e -> e.getType() != null)
                .filter(e -> e.getType().toString().equalsIgnoreCase(employeeType))
                .collect(Collectors.toList()));
    }
    
    private void filterByName(List<Employee> filteredFriends, String nameFilter) {
        if (StringUtils.isBlank(nameFilter))
            return;
        
        filteredFriends.retainAll(filteredFriends.stream()
                .filter(e -> e.getFullName().toLowerCase().contains(nameFilter.toLowerCase()))
                .collect(Collectors.toList()));
    }
}
