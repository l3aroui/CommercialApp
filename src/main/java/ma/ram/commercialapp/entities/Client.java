package ma.ram.commercialapp.entities;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;


@Entity
@AllArgsConstructor @NoArgsConstructor @Builder
@Getter @Setter
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String photoUrl;
    @OneToMany(mappedBy = "client",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Address> addresses;
    @OneToMany(mappedBy = "client",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Note> notes;
    @OneToMany(mappedBy = "client",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<PhoneNumber> phoneNumbers;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "client",orphanRemoval = true)
    private List<Sale> sales;
    @ManyToOne
    @JoinColumn(name = "commercial_id")
    private Commercial commercial;
}
