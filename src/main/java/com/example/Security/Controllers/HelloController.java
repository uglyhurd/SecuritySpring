package com.example.Security.Controllers;

import com.example.Security.Repositories.PersonRepositories;
import com.example.Security.Security.PersonDetails;
import com.example.Security.Services.AdminService;
import org.apache.tomcat.util.collections.CaseInsensitiveKeyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

    private final PersonRepositories personRepositories;
    private final AdminService adminService;

    @Autowired
    public HelloController(PersonRepositories personRepositories, AdminService adminService) {
        this.personRepositories = personRepositories;
        this.adminService = adminService;
    }

    @GetMapping("/hello")
    public String hello(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        System.out.println(personDetails);
        return "hello";
    }

    @GetMapping("/adminPage")
    public String adminPage(Model model){
        model.addAttribute("people", personRepositories.findAll());
        adminService.doAdminStuff();
        return "adminPage";
    }

}
