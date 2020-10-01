package br.com.TesteTokenLab.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

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
    private Long id;
    @Column(name = "event_title")
    private String title;
    @Column(name = "event_description")
    private String description;
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    @Column(name = "event_beginDate")
    private LocalDateTime beginDate;
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    @Column(name = "event_endDate")
    private LocalDateTime endDate;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
