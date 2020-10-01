package br.com.TesteTokenLab.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import br.com.TesteTokenLab.model.Event;
import br.com.TesteTokenLab.model.User;
import br.com.TesteTokenLab.service.UserService;

@RestController
public class DefaultController {

    private User userLogged;
    @Autowired
    private UserService userService;

    private ModelAndView mv = new ModelAndView();

    @GetMapping({ "/", "/login" })
    public ModelAndView login() {
        mv.setViewName("login");
        return mv;
    }

    @GetMapping("/registration")
    public ModelAndView registration() {
        User user = new User();
        mv.addObject("successMessage", "");
        mv.addObject("user", user);
        mv.setViewName("registration");
        return mv;
    }

    @PostMapping("/registration")
    public ModelAndView newUser(User user) {
        User userExists = userService.findUserByUsername(user.getUsername());
        if (userExists != null) {
            mv.addObject("successMessage", "Username already exists");
            mv.setViewName("registration");
        } else {
            userService.saveUser(user);
            mv.addObject("successMessage", "User Created");
            mv.addObject("user", new User());
            mv.setViewName("registration");
        }
        return mv;
    }

    @GetMapping("/user/home")
    public ModelAndView userHome() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        userLogged = userService.findUserByUsername(auth.getName());
        mv.addObject("eventEditBegin", "");
        mv.addObject("eventEditEnd", "");
        mv.addObject("eventEdit", null);
        mv.addObject("eventForm", "Create New Event");
        mv.addObject("eventFormSubmit", "Create");
        mv.addObject("eventFormUrl", "/user/createEvent");
        mv.addObject("allEvents", userService.getAllEventsByUserId(userLogged.getId()));
        mv.addObject("loggedUser", userLogged);
        mv.setViewName("user/home");
        return mv;
    }

    @PostMapping("/user/createEvent")
    public ModelAndView createEvent(@RequestParam("event_title") String event_title,
            @RequestParam("event_description") String event_description,
            @RequestParam("dateBegin") String event_dateBegin, @RequestParam("dateEnd") String event_dateEnd) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime dateBegin = LocalDateTime.parse(event_dateBegin, formatter);
        LocalDateTime dateEnd = LocalDateTime.parse(event_dateEnd, formatter);
        Event event = new Event();
        event.setTitle(event_title);
        event.setDescription(event_description);
        event.setBeginDate(dateBegin);
        event.setEndDate(dateEnd);
        event.setUser(userLogged);
        userService.saveEvent(event);
        return new ModelAndView(new RedirectView("home"));
    }

    @GetMapping("user/deleteEvent{id}")
    public ModelAndView deleteEvent(@RequestParam("id") Long id){
        userService.deleteEventById(id);
        return new ModelAndView(new RedirectView("home"));
    }

    @GetMapping("user/editEvent{id}")
    public ModelAndView editEvent(@RequestParam("id") Long id){
        mv.addObject("eventForm", "Edit Event");
        mv.addObject("eventFormSubmit", "Edit");
        mv.addObject("eventFormUrl", "/user/editEvent");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        Event event = userService.findEventById(id);
        mv.addObject("eventEditBegin", event.getBeginDate().format(formatter));
        mv.addObject("eventEditEnd", event.getEndDate().format(formatter));
        mv.addObject("eventEdit", event);
        mv.setViewName("user/home");
        return mv;
    }
    @PostMapping("/user/editEvent")
    public ModelAndView editEventProcess(@RequestParam("event_id") Long event_id, @RequestParam("event_title") String event_title,
            @RequestParam("event_description") String event_description,
            @RequestParam("dateBegin") String event_dateBegin, @RequestParam("dateEnd") String event_dateEnd) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime dateBegin = LocalDateTime.parse(event_dateBegin, formatter);
        LocalDateTime dateEnd = LocalDateTime.parse(event_dateEnd, formatter);
        Event event = new Event();
        event.setId(event_id);
        event.setTitle(event_title);
        event.setDescription(event_description);
        event.setBeginDate(dateBegin);
        event.setEndDate(dateEnd);
        event.setUser(userLogged);
        userService.saveEvent(event);
        return new ModelAndView(new RedirectView("home"));
    }

}