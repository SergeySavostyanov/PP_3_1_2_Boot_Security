package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.services.AdminService;
import ru.kata.spring.boot_security.demo.services.UserService;

@Controller
public class HelloController {
    private final UserService userService;
    private final AdminService adminService;

    public HelloController(UserService userService, AdminService adminService) {
        this.userService = userService;
        this.adminService = adminService;
    }

    @GetMapping("/hello")
    public String sayHello(){
        return "hello";
    }

    @GetMapping("/admin")
    public String adminPageP(Model model){
        adminService.doAdminStuff();
        model.addAttribute("listUsers", userService.listUsers());
        return "admin";
    }

    @GetMapping("/user-update/{id}")
    public String editForm(@PathVariable("id") Long id, Model model) {
        adminService.doAdminStuff();
        User user = userService.getById(id);
        model.addAttribute("user", user);
        return "/user-update";
    }

    @PostMapping("/user-update")
    public String editUser(User person) {
        adminService.doAdminStuff();
        userService.saveUser(person);
        return "redirect:/admin";
    }
    @GetMapping(value = "/delete")
    public String deleteUser(@RequestParam(value = "id") Long id, User user, Model model) {
        adminService.doAdminStuff();
        model.addAttribute("user", user);
        userService.removeUser(id);
        return "redirect:/admin";
    }


}
