package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.services.AdminService;
import ru.kata.spring.boot_security.demo.services.UserServiceImpl;
import ru.kata.spring.boot_security.demo.services.RegistrationService;
import ru.kata.spring.boot_security.demo.util.PersonValidator;

@RequestMapping("/admin")
@Controller
public class AdminController {
    private final UserServiceImpl personService;
    private final RegistrationService registrationService;
    private final AdminService adminService;

    public AdminController(UserServiceImpl personService, RegistrationService registrationService, AdminService adminService) {
        this.personService = personService;
        this.registrationService = registrationService;
        this.adminService = adminService;
    }



    @GetMapping(value = "/add")
    public String greetingForm(Model model) {
        model.addAttribute("user", new User());
        return "/admin/add";
    }

    @PostMapping(value = "/add")
    public String greetingSubmit(@ModelAttribute User user, Model model) {
        model.addAttribute("person", user);
        registrationService.register(user);
        return "/admin/result";
    }


    @GetMapping(value = "/delete")
    public String deleteUser(@RequestParam(value = "id") Long id, User user, Model model) {

        adminService.doAdminStuff();
        model.addAttribute("user", user);
        personService.removeUser(id);
        return "redirect:/admin";
    }

}
