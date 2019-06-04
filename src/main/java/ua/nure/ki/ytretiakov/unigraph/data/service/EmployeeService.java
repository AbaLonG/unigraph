package ua.nure.ki.ytretiakov.unigraph.data.service;

import ua.nure.ki.ytretiakov.unigraph.data.model.Employee;
import ua.nure.ki.ytretiakov.unigraph.data.model.enumeration.EmployeeType;

import java.util.List;

public interface EmployeeService extends Service<Employee, String> {

    boolean employeeExists(String login, String email);

    boolean existsByEmail(String email);

    void addFriend(String login, Employee ... friends);
    
    List<Employee> findEmployeesByType(EmployeeType type);
    
    List<Employee> findAllTeachers();
}
