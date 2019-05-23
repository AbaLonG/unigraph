package ua.nure.ki.ytretiakov.unigraph.data.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UnigraphService {

    private static Logger logger = Logger.getLogger(UnigraphService.class);

    private EmployeeService employeeService;
    private GroupService groupService;
    private CathedraService cathedraService;
    private FacultyService facultyService;

    @Autowired
    public UnigraphService(final EmployeeService employeeService,
                           final GroupService groupService,
                           final CathedraService cathedraService,
                           final FacultyService facultyService) {
        this.employeeService = employeeService;
        this.groupService = groupService;
        this.cathedraService = cathedraService;
        this.facultyService = facultyService;
    }

    public void clearDb() {
        try {
            employeeService.deleteAll();
            groupService.deleteAll();
            cathedraService.deleteAll();
            facultyService.deleteAll();
        } catch (final Exception e) {
            logger.info("Error during clearing DB");
            logger.info(e);
        }
    }

    public EmployeeService getEmployeeService() {
        return employeeService;
    }

    public GroupService getGroupService() {
        return groupService;
    }

    public CathedraService getCathedraService() {
        return cathedraService;
    }

    public FacultyService getFacultyService() {
        return facultyService;
    }
}
