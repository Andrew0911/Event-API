package org.example.proiectlaborator.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String description;

    private double rating;

    private String date;

    private String hour;

    @Column(name = "base_ticket_price")
    private Double baseTicketPrice;

    @Column(name = "ticket_price")
    private Double ticketPrice;

    @Column(name = "ticket_number")
    private Integer ticketNumber;

    @ManyToOne
    @JoinColumn(name = "organizer_id", nullable = false)
    @JsonManagedReference
    private Organizer organizer;

    @ManyToOne
    @JoinColumn(name = "venue_id", nullable = false)
    @JsonManagedReference
    private Venue venue;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Ticket> tickets;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Review> reviews;

}
