package com.gabriellazar.projectmanagement.controllers;


import com.gabriellazar.projectmanagement.dto.UserDTO;
import com.gabriellazar.projectmanagement.services.RoleService;
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

    @Autowired
    public UserController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/create-user")
    public String getCreateUser(Model model){
        model.addAttribute("user",new UserDTO());
        model.addAttribute("roles",roleService.getAllRoles());
        return "/administration/user/create-user";
    }

    @PostMapping("/create-user")
    public String insertUser(UserDTO userDTO,Model model){
//        boolean check = false;
//        if(!userDTO.getPassword().equals(userDTO.getConfirmPassword())){
//            check = true;
//            model.addAttribute("check",check);
//        }

        System.out.println(userDTO.toString());
        model.addAttribute("user",new UserDTO());
        model.addAttribute("roles",roleService.getAllRoles());
        return "/administration/user/create-user";
    }
}
