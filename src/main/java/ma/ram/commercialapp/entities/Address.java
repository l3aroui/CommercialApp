package ma.ram.commercialapp.entities;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String country="morocco";
    private String city;
    private String district;
    private String postalCode;
    @ManyToOne
    @JoinColumn(name = "commercial_id")
    private Commercial commercial;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
}
