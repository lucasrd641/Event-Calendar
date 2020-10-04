package br.com.TesteTokenLab.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "EventUserRelation")
public class EventUserRelation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "eur_id")
    private Long id;

    @Column(name = "eur_accepted",columnDefinition = "tinyint default false")
    private boolean accepted;

    @Column(name = "eur_sent",columnDefinition = "tinyint default false")
    private boolean sent;

    @ManyToOne
	@JoinColumn(name = "user_id")
	User user;
	 
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "event_id")
	Event event;
    
}