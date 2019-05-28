package ua.nure.ki.ytretiakov.unigraph.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/settings")
public class SettingsController {

    @GetMapping
    public String showPage() {
        return "redirect:/login";
    }

    @PostMapping("/{command}")
    public String logOut(@PathVariable("command") String command, HttpServletRequest request) {
        return request.getSession().getAttribute("user") == null ?
                "redirect:/login" : executeCommand(command, request);
    }

    private String executeCommand(String command, HttpServletRequest request) {
        if ("logout".equals(command)) {
            request.getSession().removeAttribute("user");
            return "redirect:/login";
        } else if ("updatePicture".equals(command)) {
            return proceedUpdatePicture(request);
        } else {
            return "redirect:/index";
        }
    }

    private String proceedUpdatePicture(HttpServletRequest request) {
        return "redirect:/index";
    }
}
