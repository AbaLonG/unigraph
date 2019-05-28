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
import ua.nure.ki.ytretiakov.unigraph.data.service.EmployeeService;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Controller
public class IndexController {

    private final static Logger logger = Logger.getLogger(IndexController.class);

    private EmployeeService employeeService;

    @Autowired
    public IndexController(EmployeeService employeeService) {
        this.employeeService = employeeService;
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
            user = refreshUser(user.getLogin());
            request.getSession().setAttribute("user", user);
            return new ModelAndView("redirect:/index?id=" + user.getLogin());
        }
    }

    private Employee refreshUser(String login) {
        return employeeService.findById(login);
    }

    @GetMapping(value = "/index", params = "id")
    public ModelAndView showPage(@RequestParam String id, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        Object userAttribute = request.getSession().getAttribute("user");
        boolean idExists = employeeService.existsById(id);
        if (idExists) {
            if (userAttribute != null) {
                Employee user = (Employee) userAttribute;
                if (user.getLogin().equals(id)) {
                    user = employeeService.findById(id);
                    request.getSession().setAttribute("user", user);
                    modelAndView.addObject("employee", user);
                    modelAndView.setViewName("index");
                }
            }
            Employee user = employeeService.findById(id);
            modelAndView.addObject("employee", user);
            modelAndView.setViewName("index");
            return modelAndView;
        } else {
            modelAndView.setViewName("redirect:/login");
            return modelAndView;
        }
    }

    @PostMapping("/index/updatePicture")
    public String updatePicture(@RequestParam("customFile") MultipartFile file, HttpServletRequest request) {
        try {
            if (file != null && !file.isEmpty()) {
                final byte[] bytes = file.getBytes();
                String rootPath = "C:\\Users\\Public\\Documents\\temp";
                File dir = new File(rootPath + File.separator + "loadFiles");
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                File uploadedFile = new File(dir.getAbsolutePath() + File.separator + file.getOriginalFilename());
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(uploadedFile));
                stream.write(bytes);
                stream.flush();
                stream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/index";
    }
}
