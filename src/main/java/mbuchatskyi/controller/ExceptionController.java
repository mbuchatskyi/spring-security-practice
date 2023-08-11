package mbuchatskyi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExceptionController {
	
    @GetMapping("/404")
    public String notFound(Model model) {
        return "error/404";
    }
}