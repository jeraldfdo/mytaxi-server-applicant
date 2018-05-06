package com.mytaxi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

/**
 * Home controller which redirects to the Swagger page
 *
 * @author jeraldfdo
 */
@Controller
@ApiIgnore
public class HomeController {

    @RequestMapping("/")
    public String home() {
        return "redirect:swagger-ui.html";
    }

}
