package ua.nure.ki.ytretiakov.unigraph.data.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.nure.ki.ytretiakov.unigraph.data.exception.DatabaseException;
import ua.nure.ki.ytretiakov.unigraph.data.model.Employee;
import ua.nure.ki.ytretiakov.unigraph.data.model.enumeration.EmployeeType;
import ua.nure.ki.ytretiakov.unigraph.data.repository.EmployeeRepository;
import ua.nure.ki.ytretiakov.unigraph.data.service.EmployeeService;

import java.util.List;
import java.util.Optional;

@Service
//@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = Exception.class)
public class EmployeeServiceImpl implements EmployeeService {

    private static Logger logger = Logger.getLogger(EmployeeServiceImpl.class);

    private EmployeeRepository repository;

    @Autowired
    public EmployeeServiceImpl(final EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(final Employee employee) {
        if (employee == null) {
            throw new DatabaseException("Employee is NULL");
        }
        if (employee.getType() == EmployeeType.Student) {
            if (employee.getGroup() == null) {
                throw new DatabaseException("Student employee type has null group");
            }
        }
        repository.saveAndFlush(employee);
    }

    @Override
    public Employee findById(final String login) {
        if (login == null) {
            throw new DatabaseException("Employee email is NULL");
        }
        final Optional<Employee> byId = repository.findById(login);
        if (byId.isPresent()) {
            return byId.get();
        } else {
            throw new DatabaseException("No employee with such login" + login);
        }
    }

    @Override
    public void deleteById(final String login) {
        if (login == null) {
            throw new DatabaseException("Login is NULL");
        }
        if (repository.existsById(login)) {
            repository.deleteById(login);
        } else {
            throw new DatabaseException("No employee with login " + login);
        }
    }

    @Override
    public boolean existsById(final String login) {
        if (login == null) {
            throw new DatabaseException("Login is NULL");
        }
        final boolean existsById = repository.existsById(login);
        logger.info("Employee " + (existsById ? "exists." : "does not exist."));
        return existsById;
    }

    @Override
    public boolean employeeExists(String login, String email) {
        if (login == null || email == null) {
            throw new DatabaseException("Null parameter");
        }
        boolean existsById = existsById(login);
        boolean existsByEmail = existsByEmail(email);
        return existsById || existsByEmail;
    }

    @Override
    public boolean existsByEmail(String email) {
        if (email == null) {
            throw new DatabaseException("Login is NULL");
        }
        final boolean existsById = repository.existsByEmail(email);
        logger.info("Employee " + (existsById ? "exists." : "does not exist."));
        return existsById;
    }

    @Override
    public void addFriend(String login, Employee... friends) {
        if (!existsById(login))
            return;
        Employee employee = findById(login);
        for (Employee friend : friends) {
            employee.getFriends().remove(friend);
            employee.getFriends().add(friend);
        }
        save(employee);
    }

    @Override
    public List<Employee> findAll() {
        return repository.findAll();
    }
    
    @Override
    public List<Employee> findEmployeesByType(EmployeeType type) {
        return repository.findEmployeesByType(type);
    }
    
    @Override
    public List<Employee> findAllTeachers() {
        return findEmployeesByType(EmployeeType.Teacher);
    }
    
    @Override
    public void deleteAll() {
        logger.info("Deleting all employees");
        repository.deleteAll();
    }
}
