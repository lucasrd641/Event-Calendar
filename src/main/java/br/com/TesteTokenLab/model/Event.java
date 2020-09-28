package br.com.TesteTokenLab.model;

import java.time.LocalDateTime;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Integer id;
    @Column(name = "event_description")
    private String description;
    @Column(name = "event_beginDate")
    private LocalDateTime beginDate;
    @Column(name = "event_endDate")
    private LocalDateTime endDate;
}
