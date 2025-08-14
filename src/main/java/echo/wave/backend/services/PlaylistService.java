package echo.wave.backend.services;

import echo.wave.backend.exceptions.BranoNotFound;
import echo.wave.backend.exceptions.PlaylistNotFound;
import echo.wave.backend.exceptions.UserNotFound;
import echo.wave.backend.models.Brano;
import echo.wave.backend.models.Playlist;
import echo.wave.backend.models.User;
import echo.wave.backend.repositories.BranoRepository;
import echo.wave.backend.repositories.PlaylistRepository;
import echo.wave.backend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class PlaylistService {

    private final PlaylistRepository playlistRepository;
    private final UserRepository userRepository;
    private final BranoRepository branoRepository;

    public Playlist creaPlaylist(String nome, String username) {
        User user = userRepository.findByUsername(username).get();
        if (user == null) {
            new UserNotFound("utente non trovato");
        }
        Date oggi = Date.valueOf(LocalDate.now());
        return playlistRepository.save(new Playlist(null, nome, oggi, user, null));
    }

    public Playlist aggiungiBranoAllaPlaylist(Long[] chiavi) {
        Playlist playlist = playlistRepository.findById(chiavi[0]).get();
        Brano brano = branoRepository.findById(chiavi[1]).get();
        if (playlist == null) {
            new PlaylistNotFound();
        } else if (brano == null) {
            new BranoNotFound();
        }
        playlist.aggiungiBrano(brano);
        return playlistRepository.save(playlist);
    }

    public Playlist rimuoviBranoDallaPlaylist(Long[] chiavi) {
        Playlist playlist = playlistRepository.findById(chiavi[0]).get();
        Brano brano = branoRepository.findById(chiavi[1]).get();
        if (playlist == null) {
            new PlaylistNotFound();
        } else if (brano == null) {
            new BranoNotFound();
        }
        playlist.rimuoviBrano(brano);
        return playlistRepository.save(playlist);
    }

}
