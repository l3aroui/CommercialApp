package ma.ram.commercialapp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime creationDate;
    private String note;
    @ManyToOne
    @JoinColumn(name="commercial_id")
    private Commercial commercial;
    @ManyToOne
    @JoinColumn(name="client_id")
    private Client client;
}
