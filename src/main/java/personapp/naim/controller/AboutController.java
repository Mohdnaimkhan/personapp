package personapp.naim.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutController {

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/hire-me")
    public String hireMe() {
        return "hire-me";
    }
}
