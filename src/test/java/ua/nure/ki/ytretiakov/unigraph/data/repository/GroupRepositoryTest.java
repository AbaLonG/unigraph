package ua.nure.ki.ytretiakov.unigraph.data.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.nure.ki.ytretiakov.unigraph.data.model.Employee;
import ua.nure.ki.ytretiakov.unigraph.data.model.enumeration.EmployeeType;
import ua.nure.ki.ytretiakov.unigraph.data.model.Group;

import java.util.Date;

public class GroupRepositoryTest extends RepositoryTester<Group, String> {

    @Autowired
    private GroupRepository repository;

    @Test
    public void testRepository() {
        assertNotNull(repository);
    }

    @Test(expected = Exception.class)
    public void testFailSaveGroupWithoutCathedra() {
        final String man = "man";
        final Employee manager = new Employee(man, man, man, man, man, new Date(), EmployeeType.Teacher);
        final Group group = new Group();
        group.setTitle("ki-15-3");
        group.setGroupManager(manager);
        final Group g = repository.saveAndFlush(group);
        assertNotNull(g);
    }

    @Override
    public JpaRepository<Group, String> getRepository() {
        return repository;
    }
}