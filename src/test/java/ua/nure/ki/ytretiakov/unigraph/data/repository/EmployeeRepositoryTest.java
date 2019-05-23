package ua.nure.ki.ytretiakov.unigraph.data.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.nure.ki.ytretiakov.unigraph.data.model.Employee;
import ua.nure.ki.ytretiakov.unigraph.data.model.enumeration.EmployeeType;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class EmployeeRepositoryTest extends RepositoryTester<Employee, String> {

    @Autowired
    private EmployeeRepository repository;

    @Test
    public void testDeleteAll() {
        repository.deleteAll();
        final List<Employee> employees = repository.findAll();
        assertNotNull(employees);
        assertTrue(employees.isEmpty());
        assertEquals(employees.size(), repository.count());
    }

    @Test
    public void testSaveEmployeeWithoutGroup() {
        final String email = "tretyak.yar@gmail.com";
        final Optional<Employee> optionalEmployee = repository.findById(email);
        if (optionalEmployee.isPresent()) {
            final Employee employeeByEmail = optionalEmployee.get();
            repository.deleteById(email);
        }
        final Employee me = new Employee("Yaroslav", "Tretiakov", new Date(), email, "password", EmployeeType.Student);
        final Employee savedMe = repository.save(me);
        assertEquals(me.getEmail(), savedMe.getEmail());
        assertFalse(repository.findAll().isEmpty());
    }

    @Test
    public void testFindByEmail() {
        final List<Employee> employees = repository.findAll();
        if (employees.isEmpty()) {
            repository.save(new Employee("User", "User", new Date(), "email", "password", EmployeeType.Student));
            assertEquals(1, repository.count());
            final Employee employeeByEmail = repository.findById("email").get();
            assertNotNull(employeeByEmail);
        } else {
            final Employee searched = employees.get(0);
            final String email = searched.getEmail();
            final Employee found = repository.findById(email).get();
            assertEquals(searched, found);
        }
    }

    @Override
    public JpaRepository<Employee, String> getRepository() {
        return repository;
    }
}