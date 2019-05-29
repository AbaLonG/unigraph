package ua.nure.ki.ytretiakov.unigraph.web.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ua.nure.ki.ytretiakov.unigraph.data.model.Employee;
import ua.nure.ki.ytretiakov.unigraph.data.model.enumeration.GenderType;
import ua.nure.ki.ytretiakov.unigraph.data.service.EmployeeService;
import ua.nure.ki.ytretiakov.unigraph.util.UnigraphUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

@Controller
public class IndexController {

    private final static Logger logger = Logger.getLogger(IndexController.class);
    private static final String MAN_AVATAR_IMAGE = "img_avatar_man.png";
    private static final String WOMAN_AVATAR_IMAGE = "img_avatar_woman.png";
    private final String UPLOAD_PATH;

    private EmployeeService employeeService;

    @Autowired
    public IndexController(EmployeeService employeeService, ServletContext servletContext) {
        this.employeeService = employeeService;
        UPLOAD_PATH = servletContext.getRealPath("/") + "resources\\" + "img";
    }

    @GetMapping({"/", "/index"})
    public ModelAndView index(HttpServletRequest request) {
        Object userAttribute = request.getSession().getAttribute("user");
        if (userAttribute == null) {
            return new ModelAndView("redirect:/login");
        }
        Employee user = (Employee) userAttribute;
        if (!employeeService.existsById(user.getLogin())) {
            return new ModelAndView("redirect:/login");
        } else {
            user = employeeService.findById(user.getLogin());
            request.getSession().setAttribute("user", user);
            return new ModelAndView("redirect:/index?id=" + user.getLogin());
        }
    }

    @GetMapping(value = "/index", params = "id")
    public ModelAndView showPage(@RequestParam String id, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        Object userAttribute = request.getSession().getAttribute("user");
        if (employeeService.existsById(id)) {
            Employee user = employeeService.findById(id);
            if (pageOfSessionUser(id, userAttribute)) {
                request.getSession().setAttribute("user", user);
            }
            modelAndView.addObject("employee", user);
            modelAndView.addObject("avatarTitle", getAvatarForEmployee(user));
            modelAndView.setViewName("index");
            return modelAndView;
        } else {
            modelAndView.setViewName("redirect:/login");
            return modelAndView;
        }
    }

    private boolean pageOfSessionUser(String requestId, Object userAttribute) {
        return userAttribute != null && ((Employee) userAttribute).getLogin().equals(requestId);
    }

    private String getAvatarForEmployee(Employee user) {
        File avatar = new File(UPLOAD_PATH + File.separator + user.getPathToAvatar());
        if (avatar.exists()) {
            return user.getPathToAvatar();
        } else {
            return user.getGenderType() == GenderType.Female ? WOMAN_AVATAR_IMAGE : MAN_AVATAR_IMAGE;
        }
    }

    @PostMapping("/index/updatePicture")
    public ModelAndView updatePicture(@RequestParam("customFile") MultipartFile file, HttpServletRequest request) {
        final ModelAndView modelAndView = new ModelAndView("redirect:/index");
        final Object userAttribute = request.getSession().getAttribute("user");
        try {
            if (file != null && !file.isEmpty() && userAttribute != null) {
                final File dir = new File(UPLOAD_PATH);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                final String hashFileName = UnigraphUtils.hashFileName(file.getBytes(), UnigraphUtils.getFormatFromName(file.getOriginalFilename()));
                final File uploadedFile = new File(dir.getAbsolutePath() + File.separator + hashFileName);
                final BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(uploadedFile));
                stream.write(file.getBytes());
                stream.flush();
                stream.close();
                Employee user = (Employee) userAttribute;
                user = employeeService.findById(user.getLogin());
                if (user == null) {
                    request.getSession().removeAttribute("user");
                    modelAndView.setViewName("redirect:/login");
                    return modelAndView;
                } else {
                    user.setPathToAvatar(hashFileName);
                    employeeService.save(user);
                    user = employeeService.findById(user.getLogin());
                    request.getSession().setAttribute("user", user);
                    modelAndView.addObject("employee", user);
                }
            }
        } catch (Exception e) {
            logger.info("Error during avatar uploading", e);
        }
        return modelAndView;
    }
}
