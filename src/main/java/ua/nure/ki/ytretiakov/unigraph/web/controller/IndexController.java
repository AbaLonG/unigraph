package ua.nure.ki.ytretiakov.unigraph.web.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class IndexController {

    private final static Logger logger = Logger.getLogger(IndexController.class);

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView greet(final HttpServletRequest request, final HttpServletResponse response) {
        logger.info("greet() method invoked");
        logger.info("Request: " + request);
        logger.info("Response: " + response);
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("name", "username");
        return modelAndView;
    }
}
