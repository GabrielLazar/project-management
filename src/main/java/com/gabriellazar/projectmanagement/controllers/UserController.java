package com.gabriellazar.projectmanagement.controllers;


import com.gabriellazar.projectmanagement.dto.UserDTO;
import java.util.*;
import com.gabriellazar.projectmanagement.services.RoleService;
import com.gabriellazar.projectmanagement.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping("/user")
public class UserController {

    private RoleService roleService;
    private UserService userService;

    @Value("${pageSize}")
    private String pageSize;

    @Autowired
    public UserController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @GetMapping("/create-user{page}")
    public String getCreateUser(@RequestParam(value = "page",required = false) Optional<Integer> pageNumber, Model model){
        int currentPage = pageNumber.orElse(1);
        int sizeOfPage = Integer.valueOf(pageSize);
        Page<UserDTO> page = userService.findPageableUser(currentPage,sizeOfPage);
        List<UserDTO> users = page.getContent();

        model.addAttribute("currentPage",currentPage);
        model.addAttribute("totalNumberOfPages",page.getTotalPages());

        model.addAttribute("users", users);
        model.addAttribute("user",new UserDTO());
        model.addAttribute("roles",roleService.getAllRoles());

        return "/administration/user/create-user";
    }

    @PostMapping("/create-user")
    public String insertUser(@Valid UserDTO userDTO, BindingResult result,Model model){

        UserDTO existingUser = userService.findUserByName(userDTO.getUserName());

        if (existingUser != null) {
            result.rejectValue("userName", null, "There is already an account registered with that username");
        }

        if (result.hasErrors()) {
            Page<UserDTO> page = userService.findPageableUser(1,5);
            List<UserDTO> users = page.getContent();

            model.addAttribute("currentPage",1);
            model.addAttribute("totalNumberOfPages",page.getTotalPages());

            model.addAttribute("users", users);
            model.addAttribute("user",new UserDTO());
            model.addAttribute("roles",roleService.getAllRoles());

            return "/administration/user/create-user";
        }
//        if(existingUser != null){
//            Page<UserDTO> page = userService.findPageableUser(1,Integer.valueOf(pageSize));
//            List<UserDTO> users = page.getContent();
//
//            model.addAttribute("currentPage",1);
//            model.addAttribute("totalNumberOfPages",page.getTotalPages());
//
//            model.addAttribute("users", users);
//            model.addAttribute("user",new UserDTO());
//            model.addAttribute("roles",roleService.getAllRoles());
//
//            model.addAttribute("existingUser",existingUser);
//
//            return "/administration/user/create-user";
//        }

         userService.saveUser(userDTO);
        return "redirect:/user/create-user";
    }

    @GetMapping("/delete-user/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        userService.deleteUserById(id);
        return "redirect:/user/create-user";
    }

    @GetMapping("/update-user/{id}")
    public String editUser(@PathVariable("id") Long id, Model model){
        Page<UserDTO> page = userService.findPageableUser(1,Integer.valueOf(pageSize));
        List<UserDTO> users = page.getContent();

        model.addAttribute("currentPage",1);
        model.addAttribute("totalNumberOfPages",page.getTotalPages());

        UserDTO userDTO = userService.findUserById(id);
        userDTO.setPassword("");
        model.addAttribute("user",userDTO);
        model.addAttribute("roles",roleService.getAllRoles());
        model.addAttribute("users",users);

        return "/administration/user/update-user";
    }

    @PostMapping("/update-user/{id}")
    public String updateUser(@PathVariable("id") Long id, UserDTO userDTO){
        userService.updateUser(id,userDTO);
        return "redirect:/user/create-user";
    }

}
