package br.com.TesteTokenLab.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.TesteTokenLab.model.Event;
import br.com.TesteTokenLab.model.Role;
import br.com.TesteTokenLab.model.User;
import br.com.TesteTokenLab.repository.EventRepository;
import br.com.TesteTokenLab.repository.RoleRepository;
import br.com.TesteTokenLab.repository.UserRepository;

@Service
public class UserService {

    private RoleRepository roleRepository;
    private UserRepository userRepository;
    private EventRepository eventRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @Autowired
    public UserService(UserRepository userRepository,
    BCryptPasswordEncoder bCryptPasswordEncoder,
    RoleRepository roleRepository,
    EventRepository eventRepository){
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleRepository = roleRepository;
        this.eventRepository = eventRepository;
    }
	public User findUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	public User saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(true);
        Role userRole = roleRepository.findByRole("USER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        return userRepository.save(user);
	}
	public void createNewEvent(Event event) {
        eventRepository.save(event);
	}
	public List<Event> getAllEventsByUserId(Integer id) {
		return eventRepository.findAllByUserId(id);
	}
    
}
