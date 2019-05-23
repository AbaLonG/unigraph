package ua.nure.ki.ytretiakov.unigraph.data.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ua.nure.ki.ytretiakov.unigraph.WebContextLoader;
import ua.nure.ki.ytretiakov.unigraph.data.model.Cathedra;
import ua.nure.ki.ytretiakov.unigraph.data.model.Employee;
import ua.nure.ki.ytretiakov.unigraph.data.model.Faculty;
import ua.nure.ki.ytretiakov.unigraph.data.model.Group;
import ua.nure.ki.ytretiakov.unigraph.data.model.enumeration.EmployeeType;

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
    public void testSaveEmployee() {
        final String dean = "dean", manager = "manager", curator = "curator", kiFaculty = "KIU", evm = "evm", ki15 = "ki-15-3";
        final Employee facultyManger = new Employee(dean, dean, new Date(), dean, dean, EmployeeType.Teacher);
        final Employee cathedraManager = new Employee(manager, manager, new Date(), manager, manager, EmployeeType.Teacher);
        final Employee groupManager = new Employee(curator, curator, new Date(), curator, curator, EmployeeType.Teacher);
        final Faculty faculty = new Faculty(kiFaculty, facultyManger);
        final Cathedra cathedra = new Cathedra(evm, cathedraManager, faculty);
        final Group group = new Group(ki15, groupManager, cathedra);
        final Employee s1 = new Employee("s1", "s1", new Date(), "s1", "s1", EmployeeType.Student);
        final Employee s2 = new Employee("s2", "s2", new Date(), "s2", "s2", EmployeeType.Student);
        final Employee s3 = new Employee("s3", "s3", new Date(), "s3", "s3", EmployeeType.Student);
        s1.setGroup(group); s2.setGroup(group); s3.setGroup(group);
        service.getEmployeeService().save(s1);
        service.getEmployeeService().save(s2);
        service.getEmployeeService().save(s3);
    }
}