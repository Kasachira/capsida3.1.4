package ru.alishev.springcourse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.alishev.springcourse.model.Role;
import ru.alishev.springcourse.model.User;
import ru.alishev.springcourse.service.RoleServiceImpl;
import ru.alishev.springcourse.service.UserServiceImpl;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Controller
@RequestMapping("")
public class MyController {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private RoleServiceImpl roleService;


    @GetMapping(value = "/admin")
    public String showAllUsers(ModelMap model) {
        List<User> list = userService.getAllUsers();
        model.addAttribute("allRoles", roleService.getAllRoles());
        model.addAttribute("allUsers", list);
        model.addAttribute("addUser", new User());
        model.addAttribute("allRoles", roleService.getAllRoles());
        return "users";
    }

    @GetMapping(value = "/user")
    public String user() {
        return "userPage";
    }


    @PostMapping(value = "/admin")
    public String addUserBd(@ModelAttribute("addUser") User user,
                            @RequestParam(value = "select_role", required = false) String[] role) {
        Set<Role> rol = new HashSet<>();
        for (String s : role) {
            if (s.equals("ROLE_ADMIN")) {
                rol.add(roleService.getAllRoles().get(0));
            } else if (s.equals("ROLE_USER")) {
                rol.add(roleService.getAllRoles().get(1));
            }
        }

        user.setRoles(rol);
        userService.save(user);
        return "redirect:/admin";
    }

    @PatchMapping(value = "/{id}")
    public String update(@ModelAttribute("user") User user,
                         @RequestParam(value = "select_roles", required = false) String[] role) {
        userService.update(user, role);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Integer id) {
        userService.delete(id);
        return "redirect:/admin";
    }
}
