package pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.mvc.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.dto.UserDTORegistration;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.dto.UserDTOUpdateProfile;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.services.UserService;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@Slf4j
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String getRegisterPage() {
        return "user-register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid UserDTORegistration userDTORegistration, BindingResult result) {
        if (result.hasErrors()){
            return "redirect:/user/register";
        }
        userService.registerUser(userDTORegistration);
        return "redirect:/";
    }

    @GetMapping("/updateProfile")
    public String getUpdateProfilePage(Model model, Principal principal){
        model.addAttribute("userProfile", userService.getUserDetails(principal.getName()));
        return "user-updateProfile";
    }

    @PostMapping("/updateProfile")
    public String updateProfile(UserDTOUpdateProfile userDTOUpdateProfile){
        log.debug("UserController-updateProfilePost: update started ...");
        if (userService.updateUserProfile(userDTOUpdateProfile)) {
            log.debug("UserController-updateProfilePost: ... update finished");
        } else {
            log.debug("UserController-updateProfilePost: ... update failed");
        }
        return "redirect:/";
    }
}
