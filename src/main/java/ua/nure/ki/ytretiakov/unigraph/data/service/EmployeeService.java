package ua.nure.ki.ytretiakov.unigraph.data.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.nure.ki.ytretiakov.unigraph.data.exception.DatabaseException;
import ua.nure.ki.ytretiakov.unigraph.data.model.Employee;
import ua.nure.ki.ytretiakov.unigraph.data.model.enumeration.EmployeeType;
import ua.nure.ki.ytretiakov.unigraph.data.repository.EmployeeRepository;

import java.util.Optional;

@Component
public class EmployeeService implements Service<Employee, String> {

    private static Logger logger = Logger.getLogger(EmployeeService.class);

    private EmployeeRepository repository;

    @Autowired
    public EmployeeService(final EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(final Employee employee) {
        if (employee == null) {
            throw new DatabaseException("Employee is NULL");
        }
        logger.info("Saving employee: " + employee.getEmail());
        if (employee.getType() == EmployeeType.Student) {
            if (employee.getGroup() == null) {
                throw new DatabaseException("Student employee type has null group");
            }
        }
        repository.saveAndFlush(employee);
        logger.info("Employee " + employee.getEmail() + " is saved");
    }

    @Override
    public Employee findById(final String employeeEmail) {
        logger.info("Looking for employee: " + employeeEmail);
        if (employeeEmail == null) {
            throw new DatabaseException("Employee email is NULL");
        }
        final Optional<Employee> byId = repository.findById(employeeEmail);
        if (byId.isPresent()) {
            logger.info("Employee with id " + employeeEmail + " is found");
            return byId.get();
        } else {
            throw new DatabaseException("No employee with such email" + employeeEmail);
        }
    }

    @Override
    public void deleteById(final String employeeEmail) {
        logger.info("Deleting employee: " + employeeEmail);
        if (employeeEmail == null) {
            throw new DatabaseException("null employee email to delete");
        }
        if (repository.existsById(employeeEmail)) {
            repository.deleteById(employeeEmail);
            logger.info("Employee has been deleted: " + employeeEmail);
        } else {
            throw new DatabaseException("No employee with email " + employeeEmail);
        }
    }

    @Override
    public void deleteAll() {
        logger.info("Deleting all employees");
        repository.deleteAll();
    }
}