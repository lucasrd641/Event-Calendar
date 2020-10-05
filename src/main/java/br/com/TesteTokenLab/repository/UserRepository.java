package br.com.TesteTokenLab.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.TesteTokenLab.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    User findByUsername(String username);

    @Query("SELECT DISTINCT u FROM User u LEFT JOIN EventUserRelation eur ON u.id=eur.user.id WHERE (u.fullName LIKE %:name_search% OR u.username LIKE %:name_search%) AND u.id<>:user_id AND u NOT IN (SELECT u FROM User u RIGHT JOIN EventUserRelation eur ON u.id=eur.user.id WHERE eur.event.id=:event_id AND (eur.sent=TRUE OR eur.accepted=TRUE))")
	List<User> findUsersByString(String name_search, Long user_id, Long event_id);
}
