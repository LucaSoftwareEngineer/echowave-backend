package echo.wave.backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "brani")
public class Brano {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBrano;
    private String nome;
    private String fileBin;

}
