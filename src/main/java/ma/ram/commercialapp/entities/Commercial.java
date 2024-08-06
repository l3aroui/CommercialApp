package ma.ram.commercialapp.entities;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter @Setter
public class Commercial{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true,length = 20,nullable = false)
    private String username;
    private String firstname;
    private String lastname;
    @Column(nullable = false,unique = true,length = 55)
    private String email;
    private String password;
    @OneToMany(mappedBy = "commercial",orphanRemoval = true,cascade = CascadeType.ALL)
    private List<PhoneNumber> phoneNumber;
    private String photoUrl;
    @OneToMany(mappedBy = "commercial",orphanRemoval = true,cascade = CascadeType.ALL)
    private List<Note> notes;
    @OneToMany(mappedBy = "commercial",orphanRemoval = true,cascade = CascadeType.ALL)
    private List<Address> addresses;
    @OneToMany(mappedBy = "commercial",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Sale> sales;
    @OneToMany(mappedBy = "commercial",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Category> categories;
    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true,mappedBy = "commercial")
    private List<Client> clients;
}