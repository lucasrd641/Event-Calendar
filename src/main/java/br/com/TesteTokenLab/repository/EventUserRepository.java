package br.com.TesteTokenLab.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.TesteTokenLab.model.EventUserRelation;

@Repository
public interface EventUserRepository extends JpaRepository<EventUserRelation, Long>{

    @Query("SELECT eur FROM EventUserRelation eur WHERE eur.user.id=:id AND eur.accepted = FALSE")
	List<EventUserRelation> getInvitesById(Long id);

    @Query("SELECT eur FROM EventUserRelation eur WHERE eur.user.id=:id")
	EventUserRelation getByUserId(Long id);

    @Query("SELECT eur FROM EventUserRelation eur WHERE eur.event.id=:event_id AND eur.user.id=:user_id")
	EventUserRelation findRelationById(Long event_id, Long user_id);
    
}
