package ua.nure.ki.ytretiakov.unigraph.application.config;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebContextConfiguration.class)
@WebAppConfiguration
public class WebContextConfigurationTest extends Assert {

    @Autowired
    private WebApplicationContext context;

    @Test
    public void testConfigurationIsRunning() {
        assertNotNull(context);
    }
}
