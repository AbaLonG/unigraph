package ua.nure.ki.ytretiakov.unigraph.web.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ua.nure.ki.ytretiakov.unigraph.data.model.Comment;
import ua.nure.ki.ytretiakov.unigraph.data.model.Employee;
import ua.nure.ki.ytretiakov.unigraph.data.model.enumeration.EmployeeType;
import ua.nure.ki.ytretiakov.unigraph.data.model.enumeration.GenderType;
import ua.nure.ki.ytretiakov.unigraph.data.service.EmployeeService;
import ua.nure.ki.ytretiakov.unigraph.data.service.GroupService;
import ua.nure.ki.ytretiakov.unigraph.data.service.UnigraphService;
import ua.nure.ki.ytretiakov.unigraph.util.UnigraphUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class IndexController {
    
    private final static Logger logger = Logger.getLogger(IndexController.class);
    private static final String MAN_AVATAR_IMAGE = "img_avatar_man.png";
    private static final String WOMAN_AVATAR_IMAGE = "img_avatar_woman.png";
    private final String UPLOAD_PATH;
    
    private UnigraphService service;
    
    @Autowired
    public IndexController(ServletContext servletContext, UnigraphService service) {
        this.service = service;
        UPLOAD_PATH = servletContext.getRealPath("/") + "resources\\" + "img";
    }
    
    @GetMapping({"/", "/index"})
    public ModelAndView index(HttpServletRequest request) {
        Object userAttribute = request.getSession().getAttribute("user");
        if (userAttribute == null) {
            return new ModelAndView("redirect:/login");
        }
        Employee user = (Employee) userAttribute;
        if (!service.getEmployeeService().existsById(user.getLogin())) {
            return new ModelAndView("redirect:/login");
        } else {
            user = service.getEmployeeService().findById(user.getLogin());
            request.getSession().setAttribute("user", user);
            return new ModelAndView("redirect:/index?id=" + user.getLogin());
        }
    }
    
    @GetMapping(value = "/index", params = "id")
    public ModelAndView showPage(@RequestParam String id, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        if (request.getSession().getAttribute("user") == null) {
            return new ModelAndView("redirect:/login");
        }
        Employee sessionUser = (Employee) request.getSession().getAttribute("user");
        if (service.getEmployeeService().existsById(id)) {
            Employee pageEmployee = service.getEmployeeService().findById(id);
            modelAndView.addObject("employee", pageEmployee);
            modelAndView.addObject("sessionUser", sessionUser);
            modelAndView.addObject("avatarTitle", getAvatarForEmployee(pageEmployee));
            modelAndView.addObject("friends", pageEmployee.getFriends());
            modelAndView.addObject("sixFriends", pageEmployee.getFriends().stream().limit(6).collect(Collectors.toList()));
            modelAndView.addObject("controller", this);
            if (pageEmployee.getType() == EmployeeType.Teacher) {
                modelAndView.addObject("groupOfTeacher", service.getGroupService().findGroupOfManager(pageEmployee.getLogin()));
            }
            modelAndView.setViewName("index");
        } else {
            modelAndView.setViewName("redirect:/login");
        }
        return modelAndView;
    }
    
    public String getAvatarForEmployee(Employee user) {
        final String dir = "resources/img/";
        File avatar = new File(UPLOAD_PATH + File.separator + user.getAvatarFile());
        if (avatar.exists()) {
            return dir + user.getAvatarFile();
        } else {
            return dir + (user.getGenderType() == GenderType.Female ? WOMAN_AVATAR_IMAGE : MAN_AVATAR_IMAGE);
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
                user = service.getEmployeeService().findById(user.getLogin());
                if (user == null) {
                    request.getSession().removeAttribute("user");
                    modelAndView.setViewName("redirect:/login");
                    return modelAndView;
                } else {
                    user.setAvatarFile(hashFileName);
                    service.getEmployeeService().save(user);
                    user = service.getEmployeeService().findById(user.getLogin());
                    request.getSession().setAttribute("user", user);
                    modelAndView.addObject("employee", user);
                }
            }
        } catch (Exception e) {
            logger.info("Error during avatar uploading", e);
        }
        return modelAndView;
    }
    
    @PostMapping(value = "/index/subscribe", params = "id")
    public String subscribe(@RequestParam String id, HttpServletRequest request) {
        Object sessionUser = request.getSession().getAttribute("user");
        if (sessionUser != null) {
            Employee user = (Employee) sessionUser;
            if (service.getEmployeeService().existsById(id)) {
                Employee toAdd = service.getEmployeeService().findById(id);
                if (service.getEmployeeService().existsById(user.getLogin())) {
                    user = service.getEmployeeService().findById(user.getLogin());
                    service.getEmployeeService().addFriend(user.getLogin(), toAdd);
                    request.getSession().setAttribute("user", user);
                }
            }
        }
        return "redirect:/index?id=" + id;
    }
    
    @PostMapping(value = "/index/unsubscribe", params = "id")
    public String unsubscribe(@RequestParam String id, HttpServletRequest request) {
        Object sessionUser = request.getSession().getAttribute("user");
        if (sessionUser != null) {
            Employee user = (Employee) sessionUser;
            if (service.getEmployeeService().existsById(id)) {
                Employee toRemove = service.getEmployeeService().findById(id);
                if (service.getEmployeeService().existsById(user.getLogin())) {
                    user = service.getEmployeeService().findById(user.getLogin());
                    user.getFriends().remove(toRemove);
                    service.getEmployeeService().save(user);
                    request.getSession().setAttribute("user", service.getEmployeeService().findById(user.getLogin()));
                }
            }
        }
        return "redirect:/index?id=" + id;
    }
    
    @PostMapping(value = "/index/comment", params = {"from", "to"})
    public String leaveComment(@RequestParam String from, @RequestParam String to, HttpServletRequest request) {
        service.getCommentRepository().save(new Comment(to, from, request.getParameter("text"), new Date()));
        return "redirect:/index?id=" + to;
    }
    
    @PostMapping(value = "index/deleteComment", params = {"id", "commentId"})
    public String deleteComment(@RequestParam String id, @RequestParam String commentId, HttpServletRequest request) {
        try {
            service.getCommentRepository().deleteById(Long.valueOf(commentId));
            return "redirect:/index?id=" + id;
        } catch (Exception e) {
            return "redirect:/index";
        }
    }
    
    public List<Comment> getCommentsFor(Employee employee) {
        List<Comment> comments = service.getCommentRepository().findCommentsByToEmployeeLogin(employee.getLogin());
        return comments == null ? Collections.EMPTY_LIST : comments;
    }
}
