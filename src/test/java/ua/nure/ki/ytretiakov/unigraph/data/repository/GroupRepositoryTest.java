package ua.nure.ki.ytretiakov.unigraph.data.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ua.nure.ki.ytretiakov.unigraph.WebContextLoader;

public class GroupRepositoryTest extends WebContextLoader {

    @Autowired
    private GroupRepository repository;

    @Test
    public void testRepository() {
        assertNotNull(repository);
    }
}