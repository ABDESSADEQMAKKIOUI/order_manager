package com.example.controller;

import com.example.entity.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    // Endpoint to display the user list
    @GetMapping("/userList")
    public String userList(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "user/GereUser";
    }

    // Endpoint to display the add user form
    @GetMapping("/addUserForm")
    public String addUserForm(Model model) {
        model.addAttribute("user", new User());
        return "addUserForm";
    }

    // Endpoint to handle the submission of the add user form
    @PostMapping("/addUser")
    public String addUser(@ModelAttribute User user) {
        userService.addUser(user);
        return "redirect:/userList";
    }

    // Endpoint to display the update user form
    @GetMapping("/updateUserForm/{id}")
    public String updateUserForm(@PathVariable Long id, Model model) {
        Optional<User> user = userService.getUserById(id);

        if (user.isPresent()) {
            model.addAttribute("user", user.get());
            return "updateUserForm";
        } else {
            // Handle user not found scenario, e.g., redirect to an error page
            return "redirect:/userList";
        }
    }

    // Endpoint to handle the submission of the update user form
    @PostMapping("/updateUser/{id}")
    public String updateUser(@PathVariable Long id, @ModelAttribute User updatedUser) {
        userService.updateUser(id, updatedUser);
        return "redirect:/userList";
    }

    // Endpoint to handle the deletion of a user
    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/userList";
    }
}
