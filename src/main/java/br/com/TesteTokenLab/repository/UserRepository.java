package br.com.TesteTokenLab.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.TesteTokenLab.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    User findByUsername(String username);

    @Query("SELECT u FROM User u WHERE (u.fullName LIKE %:name_search% OR u.username LIKE %:name_search%) AND u NOT IN (SELECT u FROM User u JOIN EventUserRelation eur ON u.id=eur.user.id WHERE eur.sent=TRUE)")
	List<User> findUsersByString(String name_search);
}
