package org.codeacademy.mantas.blogapp.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(Principal principal) {

        if (principal != null) {
            return "redirect:/";
        } else {

            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            if (!username.equals("anonymousUser")) {
                return "redirect:/";
            } else {
                return "login";
            }
        }
    }
}
