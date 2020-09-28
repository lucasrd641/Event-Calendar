package br.com.TesteTokenLab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.com.TesteTokenLab.model.User;
import br.com.TesteTokenLab.service.UserService;

@RestController
public class DefaultController {

    @Autowired
    private UserService userService;

    private ModelAndView mv = new ModelAndView();

    @GetMapping({"/","/login"})
    public ModelAndView login(){
        mv.setViewName("login");
        return mv;
    }

    @GetMapping("/registration")
    public ModelAndView registration(){
        User user = new User();
        mv.addObject("successMessage", "");
        mv.addObject("user", user);
        mv.setViewName("registration");
        return mv;
    }

    @PostMapping("/registration")
    public ModelAndView newUser(User user){
        User userExists = userService.findUserByUsername(user.getUsername());       
        if(userExists!=null){
            mv.addObject("successMessage", "Username already exists");
            mv.setViewName("registration");
        }else{
            userService.saveUser(user);
            mv.addObject("successMessage", "User Created");
            mv.addObject("user", new User());
            mv.setViewName("registration");
        }
        return mv;
    }

    @GetMapping("/user/home")
    public ModelAndView userHome(){
        mv.setViewName("user/home");
        return mv;
    }
    
}