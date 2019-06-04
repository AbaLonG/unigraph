package ua.nure.ki.ytretiakov.unigraph.data.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.nure.ki.ytretiakov.unigraph.data.model.Cathedra;
import ua.nure.ki.ytretiakov.unigraph.data.model.Employee;
import ua.nure.ki.ytretiakov.unigraph.data.model.Faculty;
import ua.nure.ki.ytretiakov.unigraph.data.model.Group;
import ua.nure.ki.ytretiakov.unigraph.data.service.*;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UnigraphServiceImpl implements UnigraphService {
    
    private EmployeeService employeeService;
    private GroupService groupService;
    private CathedraService cathedraService;
    private FacultyService facultyService;
    
    @Autowired
    public UnigraphServiceImpl(final EmployeeService employeeService,
                               final GroupService groupService,
                               final CathedraService cathedraService,
                               final FacultyService facultyService) {
        this.employeeService = employeeService;
        this.groupService = groupService;
        this.cathedraService = cathedraService;
        this.facultyService = facultyService;
    }
    
    @Override
    public List<Employee> getFreeManagersForFaculty() {
        List<Employee> teachers = employeeService.findAllTeachers();
        List<Employee> facultiesManagers = facultyService.findAll()
                .stream()
                .map(Faculty::getFacultyManager)
                .collect(Collectors.toList());
        teachers.removeAll(facultiesManagers);
        return teachers;
    }
    
    @Override
    public List<Employee> getFreeManagersForCathedra() {
        List<Employee> teachers = employeeService.findAllTeachers();
        List<Employee> cathedrasTeachers = cathedraService.findAll()
                .stream()
                .map(Cathedra::getCathedraManager)
                .collect(Collectors.toList());
        teachers.removeAll(cathedrasTeachers);
        return teachers;
    }
    
    @Override
    public List<Employee> getFreeManagersForGroup() {
        List<Employee> teachers = employeeService.findAllTeachers();
        List<Employee> groupsManagers = groupService.findAll()
                .stream()
                .map(Group::getGroupManager)
                .collect(Collectors.toList());
        teachers.removeAll(groupsManagers);
        return teachers;
    }
    
    @Override
    public EmployeeService getEmployeeService() {
        return employeeService;
    }
    
    @Override
    public GroupService getGroupService() {
        return groupService;
    }
    
    @Override
    public CathedraService getCathedraService() {
        return cathedraService;
    }
    
    @Override
    public FacultyService getFacultyService() {
        return facultyService;
    }
}
