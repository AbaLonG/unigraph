package ua.nure.ki.ytretiakov.unigraph.data.service;

import ua.nure.ki.ytretiakov.unigraph.data.model.Employee;
import ua.nure.ki.ytretiakov.unigraph.data.repository.CommentRepository;

import java.util.List;

public interface UnigraphService {

    EmployeeService getEmployeeService();

    GroupService getGroupService();

    CathedraService getCathedraService();

    FacultyService getFacultyService();
    
    List<Employee> getFreeManagersForFaculty();
    
    List<Employee> getFreeManagersForCathedra();
    
    List<Employee> getFreeManagersForGroup();
    
    CommentRepository getCommentRepository();
}
