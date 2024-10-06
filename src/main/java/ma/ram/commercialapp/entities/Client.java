package ma.ram.commercialapp.entities;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;


@Entity
@AllArgsConstructor @NoArgsConstructor @Builder
@Getter @Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
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
    @OneToMany(mappedBy = "client",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
    private List<PhoneNumber> phoneNumbers;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "client",orphanRemoval = true)
    private List<Sale> sales;
    @ManyToOne
    @JoinColumn(name = "commercial_id",nullable = false)
    @JsonIgnore
    private Commercial commercial;
}
