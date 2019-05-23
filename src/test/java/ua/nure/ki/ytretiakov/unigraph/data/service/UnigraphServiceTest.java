package ua.nure.ki.ytretiakov.unigraph.data.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ua.nure.ki.ytretiakov.unigraph.WebContextLoader;

public class UnigraphServiceTest extends WebContextLoader {

    @Autowired
    private UnigraphService service;

    @Before
    public void deleteAll() {
        service.clearDb();
    }

    @Test
    public void testUnigraphService() {
        assertNotNull(service);
    }

    @Test
    public void testAddEmployee() {
        assertTrue(true);
    }
}