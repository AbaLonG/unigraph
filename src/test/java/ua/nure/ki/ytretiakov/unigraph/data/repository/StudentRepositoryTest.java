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
@WebAppConfiguration
@ContextConfiguration(classes = WebContextConfiguration.class)
public class StudentRepositoryTest extends Assert {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void testRepository() {
        assertNotNull(studentRepository);
        System.out.println("Student repository: " + studentRepository);
    }
}