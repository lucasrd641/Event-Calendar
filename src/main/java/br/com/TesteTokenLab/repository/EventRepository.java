package br.com.TesteTokenLab.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.TesteTokenLab.model.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long>{

    
    @Query("SELECT e FROM Event e WHERE e.user.id = :id OR e IN (SELECT e FROM Event e JOIN EventUserRelation eur ON e.id=eur.event.id WHERE eur.user.id =:id AND eur.accepted=TRUE)")
	List<Event> findAllByUserId(Long id);
    
}
