package br.com.TesteTokenLab.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.TesteTokenLab.model.Event;
import br.com.TesteTokenLab.model.EventUserRelation;
import br.com.TesteTokenLab.model.Role;
import br.com.TesteTokenLab.model.User;
import br.com.TesteTokenLab.repository.EventRepository;
import br.com.TesteTokenLab.repository.EventUserRepository;
import br.com.TesteTokenLab.repository.RoleRepository;
import br.com.TesteTokenLab.repository.UserRepository;

@Service
public class UserService {

    private RoleRepository roleRepository;
    private UserRepository userRepository;
    private EventUserRepository eventUserRepository;
    private EventRepository eventRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @Autowired
    public UserService(UserRepository userRepository,
    EventUserRepository eventUserRepository,
    BCryptPasswordEncoder bCryptPasswordEncoder,
    RoleRepository roleRepository,
    EventRepository eventRepository){
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleRepository = roleRepository;
        this.eventRepository = eventRepository;
        this.eventUserRepository = eventUserRepository;
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
	public void saveEvent(Event event) {
        eventRepository.save(event);
	}
	public List<Event> getAllEventsByUserId(Long id) {
		return eventRepository.findAllByUserId(id);
	}
	public void deleteEventById(Long id) {
        eventRepository.deleteById(id);
	}
	public Event findEventById(Long id) {
		return eventRepository.getOne(id);
	}
	public List<EventUserRelation> getInvitesById(Long id) {
                return eventUserRepository.getInvitesById(id);
	}
	public List<User> findUserByString(String name_search, Long user_id, Long event_id) {
		return userRepository.findUsersByString(name_search,user_id,event_id);
	}
	public User findUserById(Long user_id) {
		return userRepository.getOne(user_id);
	}
	public void sendInvite(EventUserRelation eur) {
        eventUserRepository.save(eur);
	}
	public EventUserRelation findRelationById(Long event_id, Long user_id) {
		return eventUserRepository.findRelationById(event_id,user_id);
	}
	public void deleteRelationById(EventUserRelation eur) {
		eventUserRepository.delete(eur);
	}
	public List<EventUserRelation> findAllRelations() {
		return eventUserRepository.findAll();
	}
    
}
