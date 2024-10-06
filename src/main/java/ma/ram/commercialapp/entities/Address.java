package ma.ram.commercialapp.entities;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
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
