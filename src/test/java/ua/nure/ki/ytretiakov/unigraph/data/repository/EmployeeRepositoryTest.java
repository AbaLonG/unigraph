package ua.nure.ki.ytretiakov.unigraph.data.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import ua.nure.ki.ytretiakov.unigraph.application.config.WebContextConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebContextConfiguration.class)
@WebAppConfiguration
public class EmployeeRepositoryTest extends Assert {

    @Autowired
    private EmployeeRepository repository;

    @Test
    public void testRepository() {
        assertNotNull(repository);
    }
}