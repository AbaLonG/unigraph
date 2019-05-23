package ua.nure.ki.ytretiakov.unigraph.data.service;

import org.junit.After;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
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

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @After
    public void deleteAll() {
        jdbcTemplate.execute("DROP TABLE public.employees CASCADE");
        jdbcTemplate.execute("DROP TABLE public.groups CASCADE");
        jdbcTemplate.execute("DROP TABLE public.cathedras CASCADE");
        jdbcTemplate.execute("DROP TABLE public.faculties CASCADE");
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
        s1.setGroup(group);
        s2.setGroup(group);
        s3.setGroup(group);
        service.getEmployeeService().save(s1);
        service.getEmployeeService().save(s2);
        service.getEmployeeService().save(s3);
        assertNotNull(service.getEmployeeService().findById(dean));
        assertNotNull(service.getEmployeeService().findById(manager));
        assertNotNull(service.getEmployeeService().findById(curator));
        assertNotNull(service.getFacultyService().findById(kiFaculty).getFacultyManager());
        assertEquals(facultyManger.getEmail(), service.getFacultyService().findById(kiFaculty).getFacultyManager().getEmail());
        assertNotNull(service.getGroupService().findById(ki15).getStudents());
        assertFalse(service.getGroupService().findById(ki15).getStudents().isEmpty());
        assertEquals(3, service.getGroupService().findById(ki15).getStudents().size());
        updateEmployeeSetOtherGroup();
    }

    public void updateEmployeeSetOtherGroup() {
        final String cur = "cur";
        final Employee manager = new Employee(cur, cur, new Date(), cur, cur, EmployeeType.Teacher);
        final Group group = new Group("ki-15-5", manager, service.getCathedraService().findById("evm"));
        service.getGroupService().save(group);
        final Employee s1 = service.getEmployeeService().findById("s1");
        s1.setGroup(group);
        service.getEmployeeService().save(s1);
        assertEquals("ki-15-5", service.getEmployeeService().findById(s1.getEmail()).getGroup().getTitle());
    }
}