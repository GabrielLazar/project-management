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
@RequestMapping("/administration")
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
        int sizeOfPage = Integer.valueOf(this.pageSize);
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
    public String insertUser(@ModelAttribute("user") @Valid UserDTO userDTO, BindingResult result,Model model){

        UserDTO existingUser = userService.findUserByUsername(userDTO.getUserName());

        if (existingUser != null) {
            result.rejectValue("userName", null, "There is already an account registered with this username! Please choose a new username.");
        }

        if (result.hasErrors()) {
            Page<UserDTO> page = userService.findPageableUser(1,Integer.valueOf(pageSize));
            List<UserDTO> users = page.getContent();
            model.addAttribute("user",userDTO);
            model.addAttribute("users", users);
            model.addAttribute("roles",roleService.getAllRoles());
            return "/administration/user/create-user";
        }

         userService.saveUser(userDTO);
        return "redirect:/administration/create-user";
    }

    @GetMapping("/delete-user/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        userService.deleteUserById(id);
        return "redirect:/administration/create-user";
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
    public String updateUser(@PathVariable("id") Long id, @ModelAttribute("user") @Valid UserDTO userDTO,BindingResult result,Model model){
        boolean checkPasswordConfirmation = !userDTO.getPassword().equals(userDTO.getConfirmPassword());
        if(checkPasswordConfirmation){
            result.rejectValue("confirmPassword", null, "Confirm password must match the password!");
        }
        if(result.hasErrors()){
            Page<UserDTO> page = userService.findPageableUser(1,Integer.valueOf(pageSize));
            List<UserDTO> users = page.getContent();
            model.addAttribute("user",userDTO);
            model.addAttribute("roles",roleService.getAllRoles());
            model.addAttribute("users",users);
            return "/administration/user/update-user";
        }

        userService.updateUser(id,userDTO);
        return "redirect:/administration/create-user";
    }

}
