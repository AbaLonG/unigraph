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

    private static final String DEAN = "dean";
    private static final String MANAGER = "manager";
    private static final String CURATOR = "CURATOR", CUR = "cur";
    private static final String KIU = "KIU";
    private static final String EVM = "evm";
    private static final String KI_3 = "ki-15-3", KI_5 = "ki-15-5";
    private static final String S1 = "s1", S2 = "s2", S3 = "S3";

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
    public void testUnigraphService() {
        saveEmployeeGroupCathedraFaculty();
        updateEmployeeSetOtherGroup();
    }

    private void saveEmployeeGroupCathedraFaculty() {
        final Employee facultyManger = new Employee(DEAN, DEAN, DEAN, DEAN, DEAN, new Date(), EmployeeType.Teacher);
        final Employee cathedraManager = new Employee(MANAGER, MANAGER, MANAGER, MANAGER, MANAGER, new Date(), EmployeeType.Teacher);
        final Employee groupManager = new Employee(CURATOR, CURATOR, CURATOR, CURATOR, CURATOR, new Date(), EmployeeType.Teacher);
        final Faculty faculty = new Faculty(KIU, facultyManger);
        final Cathedra cathedra = new Cathedra(EVM, cathedraManager, faculty);
        final Group group = new Group(KI_5, groupManager, cathedra);
        final Employee s1 = new Employee(S1, S1, S1, S1, S1, new Date(), EmployeeType.Student);
        final Employee s2 = new Employee(S2, S2, S2, S2, S2, new Date(), EmployeeType.Student);
        final Employee s3 = new Employee(S3, S3, S3, S3, S3, new Date(), EmployeeType.Student);
        s1.setGroup(group);
        s2.setGroup(group);
        s3.setGroup(group);
        service.getEmployeeService().save(s1);
        service.getEmployeeService().save(s2);
        service.getEmployeeService().save(s3);
        assertNotNull(service.getEmployeeService().findById(DEAN));
        assertNotNull(service.getEmployeeService().findById(MANAGER));
        assertNotNull(service.getEmployeeService().findById(CURATOR));
        assertNotNull(service.getFacultyService().findById(KIU).getFacultyManager());
        assertEquals(facultyManger.getEmail(), service.getFacultyService().findById(KIU).getFacultyManager().getEmail());
        assertNotNull(service.getGroupService().findById(KI_5).getStudents());
        assertFalse(service.getGroupService().findById(KI_5).getStudents().isEmpty());
        assertEquals(3, service.getGroupService().findById(KI_5).getStudents().size());
    }

    private void updateEmployeeSetOtherGroup() {
        final Employee manager = new Employee(CUR, CUR, CUR, CUR, CUR, new Date(), EmployeeType.Teacher);
        final Group group = new Group(KI_3, manager, service.getCathedraService().findById(EVM));
        service.getGroupService().save(group);
        final Employee s1 = service.getEmployeeService().findById(S1);
        s1.setGroup(group);
        service.getEmployeeService().save(s1);
        assertEquals(KI_3, service.getEmployeeService().findById(s1.getEmail()).getGroup().getTitle());
    }
}