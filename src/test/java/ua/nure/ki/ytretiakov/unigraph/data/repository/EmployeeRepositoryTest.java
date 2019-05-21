package ua.nure.ki.ytretiakov.unigraph.data.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ua.nure.ki.ytretiakov.unigraph.WebContextLoader;

public class EmployeeRepositoryTest extends WebContextLoader {

    @Autowired
    private EmployeeRepository repository;

    @Test
    public void testRepository() {
        assertNotNull(repository);
    }
}