package com.gabriellazar.projectmanagement.controllers;


import com.gabriellazar.projectmanagement.dto.UserDTO;
import com.gabriellazar.projectmanagement.services.RoleService;
import com.gabriellazar.projectmanagement.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/user")
public class UserController {

    private RoleService roleService;
    private UserService userService;

    @Autowired
    public UserController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @GetMapping("/create-user")
    public String getCreateUser(Model model){
        model.addAttribute("user",new UserDTO());
        model.addAttribute("roles",roleService.getAllRoles());
        model.addAttribute("users",userService.getAllUsers());
        return "/administration/user/create-user";
    }

    @PostMapping("/create-user")
    public String insertUser(UserDTO userDTO,Model model){
//        boolean check = false;
//        if(!userDTO.getPassword().equals(userDTO.getConfirmPassword())){
//            check = true;
//            model.addAttribute("check",check);
//        }

        userService.saveUser(userDTO);
        model.addAttribute("user",new UserDTO());
        model.addAttribute("roles",roleService.getAllRoles());
        model.addAttribute("users",userService.getAllUsers());
        return "/administration/user/create-user";
    }
}