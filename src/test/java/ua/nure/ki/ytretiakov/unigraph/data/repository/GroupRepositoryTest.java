package ua.nure.ki.ytretiakov.unigraph.data.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.nure.ki.ytretiakov.unigraph.data.model.Employee;
import ua.nure.ki.ytretiakov.unigraph.data.model.EmployeeType;
import ua.nure.ki.ytretiakov.unigraph.data.model.Group;

import java.util.Date;

public class GroupRepositoryTest extends RepositoryTester<Group, Long> {

    @Autowired
    private GroupRepository repository;

    @Test
    public void testRepository() {
        assertNotNull(repository);
    }

    @Test
    public void testSaveGroup() {
        final Employee manager = new Employee("man", "man", new Date(), "man", "pass", EmployeeType.Teacher);
        final Group group = new Group();
        group.setTitle("ki-15-3");
        group.setGroupManager(manager);
        final Group g = repository.saveAndFlush(group);
        assertNotNull(g);
    }

    @Override
    public JpaRepository<Group, Long> getRepository() {
        return repository;
    }
}