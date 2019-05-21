package ua.nure.ki.ytretiakov.unigraph.data.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.nure.ki.ytretiakov.unigraph.data.model.Group;

public class GroupRepositoryTest extends RepositoryTester<Group, Long> {

    @Autowired
    private GroupRepository repository;

    @Test
    public void testRepository() {
        assertNotNull(repository);
    }

    @Override
    public JpaRepository<Group, Long> getRepository() {
        return repository;
    }
}