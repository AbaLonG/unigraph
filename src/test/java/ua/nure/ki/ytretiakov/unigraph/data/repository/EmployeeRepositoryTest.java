package ua.nure.ki.ytretiakov.unigraph.data.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.nure.ki.ytretiakov.unigraph.data.model.Employee;
import ua.nure.ki.ytretiakov.unigraph.data.model.EmployeeType;

import java.util.Date;
import java.util.List;

public class EmployeeRepositoryTest extends RepositoryTester<Employee, Long> {

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
    public void testSaveEmployee() {
        final String email = "tretyak.yar@gmail.com";
        final Employee employeeByEmail = repository.findEmployeeByEmail(email);
        if (employeeByEmail != null) {
            repository.deleteByEmail(email);
        }
        final Employee me = new Employee("Yaroslav", "Tretiakov", new Date(), email, "password", EmployeeType.Student);
        final Employee savedMe = repository.save(me);
        assertNotNull(savedMe.getId());
        assertEquals(me.getEmail(), savedMe.getEmail());
        assertFalse(repository.findAll().isEmpty());
    }

    @Test
    public void testFindByEmail() {
        final List<Employee> employees = repository.findAll();
        if (employees.isEmpty()) {
            repository.save(new Employee("User", "User", new Date(), "email", "password", EmployeeType.Student));
            assertEquals(1, repository.count());
            final Employee employeeByEmail = repository.findEmployeeByEmail("email");
            assertNotNull(employeeByEmail);
        } else {
            final Employee searched = employees.get(0);
            final String email = searched.getEmail();
            final Employee found = repository.findEmployeeByEmail(email);
            assertEquals(searched, found);
        }
    }

    @Override
    public JpaRepository<Employee, Long> getRepository() {
        return repository;
    }
}