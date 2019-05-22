package ua.nure.ki.ytretiakov.unigraph.data.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ua.nure.ki.ytretiakov.unigraph.WebContextLoader;
import ua.nure.ki.ytretiakov.unigraph.data.model.Employee;
import ua.nure.ki.ytretiakov.unigraph.data.model.EmployeeType;
import ua.nure.ki.ytretiakov.unigraph.data.model.Group;

import java.util.Date;

public class UnigraphServiceTest extends WebContextLoader {

    @Autowired
    private UnigraphService service;

    @Before
    public void deleteAll() {
        service.clearDb();
    }

    @Test
    public void testUnigraphService() {
        assertNotNull(service);
    }

    @Test
    public void testAddEmployee() {
        final String user1 = "user1", man1 = "man1";
        Employee manager = new Employee(man1, man1, new Date(), man1, man1, EmployeeType.Teacher);
        Group group = new Group("ki-15-3");
        group.setGroupManager(manager);
        Employee employee = new Employee(user1, user1, new Date(), user1, user1, EmployeeType.Student);
        employee.setGroup(group);
        service.addEmployee(employee);
    }
}