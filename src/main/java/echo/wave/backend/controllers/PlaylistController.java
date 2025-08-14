package echo.wave.backend.controllers;

import echo.wave.backend.dto.CreaPlaylistRequest;
import echo.wave.backend.models.Playlist;
import echo.wave.backend.security.JwtUtil;
import echo.wave.backend.services.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/playlist")
@RequiredArgsConstructor
public class PlaylistController {

    private final PlaylistService playlistService;
    private final JwtUtil jwtUtil;

    @PostMapping("/crea")
    public ResponseEntity<Playlist> creaPlaylist(
            @RequestHeader("Authorization") String token,
            @RequestBody CreaPlaylistRequest json
    ) {
        token = token.substring(7);
        String username = jwtUtil.extractUsername(token);
        return ResponseEntity.ok().body(playlistService.creaPlaylist(json.getNome(), username));
    }

}
