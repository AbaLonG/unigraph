package ua.nure.ki.ytretiakov.unigraph.application.config;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import ua.nure.ki.ytretiakov.unigraph.WebContextLoader;

public class WebContextConfigurationTest extends WebContextLoader {

    @Autowired
    private WebApplicationContext context;

    @Test
    public void testConfigurationIsRunning() {
        assertNotNull(context);
    }
}
