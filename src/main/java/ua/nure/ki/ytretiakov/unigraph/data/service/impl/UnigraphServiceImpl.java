package ua.nure.ki.ytretiakov.unigraph.data.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.nure.ki.ytretiakov.unigraph.data.service.*;

@Service
public class UnigraphServiceImpl implements UnigraphService {

    private static Logger logger = Logger.getLogger(UnigraphServiceImpl.class);

    private EmployeeService employeeService;
    private GroupService groupService;
    private CathedraService cathedraService;
    private FacultyService facultyService;

    @Autowired
    public UnigraphServiceImpl(final EmployeeService employeeService,
                               final GroupService groupService,
                               final CathedraService cathedraService,
                               final FacultyService facultyService) {
        this.employeeService = employeeService;
        this.groupService = groupService;
        this.cathedraService = cathedraService;
        this.facultyService = facultyService;
    }

    @Override
    public EmployeeService getEmployeeService() {
        return employeeService;
    }

    @Override
    public GroupService getGroupService() {
        return groupService;
    }

    @Override
    public CathedraService getCathedraService() {
        return cathedraService;
    }

    @Override
    public FacultyService getFacultyService() {
        return facultyService;
    }
}
