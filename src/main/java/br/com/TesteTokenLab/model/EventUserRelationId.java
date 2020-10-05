package br.com.TesteTokenLab.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;

import lombok.*;

/**
 * EventUserRelationId
 */
@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class EventUserRelationId implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Column(name = "user_id")
    private Long user_Id;
    @Column(name = "event_id")
    private Long event_Id;
    
}