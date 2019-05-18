package ua.nure.ki.ytretiakov.unigraph.initialization;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import ua.nure.ki.ytretiakov.unigraph.config.UnigraphConfiguration;

public class UnigraphInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{UnigraphConfiguration.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{UnigraphConfiguration.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
