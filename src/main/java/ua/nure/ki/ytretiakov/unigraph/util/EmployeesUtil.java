package ua.nure.ki.ytretiakov.unigraph.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.nure.ki.ytretiakov.unigraph.data.model.Employee;
import ua.nure.ki.ytretiakov.unigraph.data.model.Faculty;
import ua.nure.ki.ytretiakov.unigraph.data.model.Group;
import ua.nure.ki.ytretiakov.unigraph.data.model.enumeration.EmployeeType;
import ua.nure.ki.ytretiakov.unigraph.data.service.UnigraphService;
import ua.nure.ki.ytretiakov.unigraph.web.controller.IndexController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeesUtil {
    
    private UnigraphService service;
    private IndexController indexController;
    
    @Autowired
    public EmployeesUtil(UnigraphService service, IndexController indexController) {
        this.service = service;
        this.indexController = indexController;
    }
    
    public String getAvatarForEmployee(Employee employee) {
        return indexController.getAvatarForEmployee(employee);
    }
    
    public void filterEmployees(HttpServletRequest request, List<Employee> filteredFriends) {
        filterByFaculty(filteredFriends, request.getParameter("facultyTitle"));
        filterByCathedra(filteredFriends, request.getParameter("cathedraTitle"));
        filterByGroup(filteredFriends, request.getParameter("groupTitle"));
        filterByType(filteredFriends, request.getParameter("employeeType"));
        filterByName(filteredFriends, request.getParameter("nameFilter"));
        filterByCount(filteredFriends, request.getParameter("countFilter"));
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
                    final Group groupOfManager = service.getGroupService().findGroupOfManager(e.getLogin());
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
    
    private void filterByCount(List<Employee> filteredFriends, String countFilter) {
        try {
            int count = Integer.valueOf(countFilter);
            filteredFriends.retainAll(filteredFriends.stream().limit(count).collect(Collectors.toList()));
        } catch (Exception e) {
            
        }
    }
}
