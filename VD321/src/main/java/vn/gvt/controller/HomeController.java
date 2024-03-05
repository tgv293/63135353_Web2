package vn.gvt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home/")
public class HomeController {

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/about")
    public String about() {
        return "about";
    }

    @RequestMapping("/contact")
    public String contact() {
        return "/contact";
    }

    @RequestMapping("/feedback")
    public String feedback() {
        return "feedback";
    }

    @RequestMapping("/faq")
    public String faq() {
        return "faq";
    }
}

