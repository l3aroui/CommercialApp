package ma.ram.commercialapp.entities;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter @Builder
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String categoryName;
    private String categoryDescription;
    @ManyToOne
    @JoinColumn(name = "commercial_id")
    private Commercial commercial;
    @OneToMany(orphanRemoval = true,cascade = CascadeType.ALL)
    private List<Client> clients;
}
