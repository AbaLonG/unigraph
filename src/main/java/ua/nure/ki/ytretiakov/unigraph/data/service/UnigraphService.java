package ua.nure.ki.ytretiakov.unigraph.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.nure.ki.ytretiakov.unigraph.data.repository.CathedraRepository;
import ua.nure.ki.ytretiakov.unigraph.data.repository.EmployeeRepository;
import ua.nure.ki.ytretiakov.unigraph.data.repository.FacultyRepository;
import ua.nure.ki.ytretiakov.unigraph.data.repository.GroupRepository;

@Component
public class UnigraphService {

    private EmployeeRepository employeeRepository;
    private GroupRepository groupRepository;
    private CathedraRepository cathedraRepository;
    private FacultyRepository facultyRepository;

    @Autowired
    public UnigraphService(final EmployeeRepository employeeRepository,
                           final GroupRepository groupRepository,
                           final CathedraRepository cathedraRepository,
                           final FacultyRepository facultyRepository) {
        this.employeeRepository = employeeRepository;
        this.groupRepository = groupRepository;
        this.cathedraRepository = cathedraRepository;
        this.facultyRepository = facultyRepository;
    }
}
