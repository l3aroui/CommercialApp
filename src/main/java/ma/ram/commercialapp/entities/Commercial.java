package ma.ram.commercialapp.entities;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter @Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Commercial{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true,nullable = false)
    private String keycloakId;
    @Column(unique = true,length = 20,nullable = false)
    private String username;
    private String firstname;
    private String lastname;
    @Column(nullable = false,unique = true,length = 55)
    private String email;
    @OneToMany(mappedBy = "commercial",orphanRemoval = true,cascade = CascadeType.ALL)
    private List<PhoneNumber> phoneNumber;
    private String photoUrl;
//    @OneToMany(mappedBy = "commercial",orphanRemoval = true,cascade = CascadeType.ALL)
//    private List<Note> notes;
    @OneToMany(mappedBy = "commercial",orphanRemoval = true,cascade = CascadeType.ALL)
    private List<Address> addresses;
    @OneToMany(mappedBy = "commercial",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
    private List<Sale> sales;
    @OneToMany(mappedBy = "commercial",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Category> categories;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "commercial")
    private List<Client> clients;
}