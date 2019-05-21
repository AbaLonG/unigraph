package ua.nure.ki.ytretiakov.unigraph;

import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import ua.nure.ki.ytretiakov.unigraph.application.config.WebContextConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebContextConfiguration.class)
@WebAppConfiguration
@Profile("dev")
public abstract class WebContextLoader extends Assert {


}
