package echo.wave.backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPlaylist;
    private String nome;
    private Date dataCreazione;

    @ManyToOne
    private User user;

    @ManyToMany
    private List<Brano> brani;

    public void aggiungiBrano(Brano brano) {
        brani.add(brano);
    }

}
