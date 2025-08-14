package echo.wave.backend.services;

import echo.wave.backend.exceptions.UserNotFound;
import echo.wave.backend.models.Playlist;
import echo.wave.backend.models.User;
import echo.wave.backend.repositories.PlaylistRepository;
import echo.wave.backend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class PlaylistService {

    private final PlaylistRepository playlistRepository;
    private final UserRepository userRepository;

    public Playlist creaPlaylist(String nome, String username) {
        User user = userRepository.findByUsername(username).get();
        if (user == null) {
            new UserNotFound("utente non trovato");
        }
        Date oggi = Date.valueOf(LocalDate.now());
        return playlistRepository.save(new Playlist(null, nome, oggi, user));
    }

}
