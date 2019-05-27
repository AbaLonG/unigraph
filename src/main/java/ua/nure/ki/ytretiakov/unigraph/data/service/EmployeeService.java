package ua.nure.ki.ytretiakov.unigraph.data.service;

import ua.nure.ki.ytretiakov.unigraph.data.model.Employee;

public interface EmployeeService extends Service<Employee, String> {

    boolean employeeExists(String login, String email);

    boolean existsByEmail(String email);
}
