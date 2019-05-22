package ua.nure.ki.ytretiakov.unigraph.data.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.nure.ki.ytretiakov.unigraph.data.exception.DatabaseException;
import ua.nure.ki.ytretiakov.unigraph.data.model.Employee;
import ua.nure.ki.ytretiakov.unigraph.data.model.EmployeeType;
import ua.nure.ki.ytretiakov.unigraph.data.repository.CathedraRepository;
import ua.nure.ki.ytretiakov.unigraph.data.repository.EmployeeRepository;
import ua.nure.ki.ytretiakov.unigraph.data.repository.FacultyRepository;
import ua.nure.ki.ytretiakov.unigraph.data.repository.GroupRepository;

import java.util.Optional;

@Component
public class UnigraphService {

    private static Logger logger = Logger.getLogger(UnigraphService.class);

    private EmployeeRepository employeeRepository;
    private GroupRepository groupRepository;
    private CathedraRepository cathedraRepository;
    private FacultyRepository facultyRepository;

    @Autowired
    public UnigraphService(final EmployeeRepository employeeRepository,
                           final GroupRepository groupRepository,
                           final CathedraRepository cathedraRepository,
                           final FacultyRepository facultyRepository) {
        this.employeeRepository = employeeRepository;
        this.groupRepository = groupRepository;
        this.cathedraRepository = cathedraRepository;
        this.facultyRepository = facultyRepository;
    }

    public void addEmployee(final Employee employee) {
        if (employee == null) {
            throw new DatabaseException("Null employee");
        }
        if (employee.getType() == EmployeeType.Student) {
            if (employee.getGroup() == null) {
                throw new DatabaseException("Student employee type has null group");
            }
        }
        employeeRepository.saveAndFlush(employee);
    }

    public void removeEmployee(final Employee employee) {
        if (employee == null) {
            throw new DatabaseException("NULL employee");
        }
        removeEmployee(employee.getEmail());
    }

    public void removeEmployee(final String employeeEmail) {
        if (employeeEmail == null) {
            throw new DatabaseException("null employee email to delete");
        }
        if (employeeRepository.existsById(employeeEmail)) {
            employeeRepository.deleteById(employeeEmail);
        } else {
            throw new DatabaseException("No employee with email " + employeeEmail);
        }
    }

    public Employee findEmployee(final String employeeEmail) {
        if (employeeEmail == null) {
            throw new DatabaseException("Employee email is NULL");
        }
        final Optional<Employee> byId = employeeRepository.findById(employeeEmail);
        if (byId.isPresent()) {
            return byId.get();
        } else {
            throw new DatabaseException("No employee with such email" + employeeEmail);
        }
    }

    public void clearDb() {
        groupRepository.deleteAll();
        employeeRepository.deleteAll();
        facultyRepository.deleteAll();
        cathedraRepository.deleteAll();
    }
}
