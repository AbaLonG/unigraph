package ua.nure.ki.ytretiakov.unigraph.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.nure.ki.ytretiakov.unigraph.data.model.Employee;
import ua.nure.ki.ytretiakov.unigraph.data.service.UnigraphService;

import java.util.Arrays;

@Component
public class UnigraphUtils {

    private UnigraphService service;

    @Autowired
    public UnigraphUtils(UnigraphService service) {
        this.service = service;
    }

    public static String hashFileName(final byte[] file, final String format) {
        final int hash = Arrays.hashCode(file);
        final String hex = Integer.toHexString(hash);
        return (hex.length() <= 10 ? hex : hex.substring(0, 10)) + format;
    }

    public static String getFormatFromName(final String originalFilename) {
        final int dotIndex = originalFilename.lastIndexOf('.');
        return originalFilename.substring(dotIndex);
    }

    public Employee syncEmployeeWithDatabase(String login) {
        if (service.getEmployeeService().existsById(login)) {
            return service.getEmployeeService().findById(login);
        }
        return null;
    }
}
