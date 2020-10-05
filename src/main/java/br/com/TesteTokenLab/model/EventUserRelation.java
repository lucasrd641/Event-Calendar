package br.com.TesteTokenLab.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "EventUserRelation")
public class EventUserRelation{
    @EmbeddedId
    private EventUserRelationId id;
 
    @Column(name = "eur_accepted",columnDefinition = "tinyint default false")
    private boolean accepted;

    @Column(name = "eur_sent",columnDefinition = "tinyint default false")
    private boolean sent;

    
    @ManyToOne
    @MapsId("user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id")
	private User user;
     
    
    @ManyToOne
    @MapsId("event_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "event_id")
	private Event event;
    
}