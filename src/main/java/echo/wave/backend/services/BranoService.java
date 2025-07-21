package echo.wave.backend.services;

import echo.wave.backend.components.Uploader;
import echo.wave.backend.models.Brano;
import echo.wave.backend.models.User;
import echo.wave.backend.repositories.BranoRepository;
import echo.wave.backend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class BranoService {

    private final BranoRepository branoRepository;
    private final UserRepository userRepository;
    private final Uploader uploader;

    public Brano aggiungiBrano(String nome, String file, Long idUser, MultipartFile binary) throws IOException {
        uploader.upload(binary, file);
        User user = userRepository.findById(idUser).get();
        if (user == null) {
            new UsernameNotFoundException("utente non trovato");
        }
        Brano brano = new Brano(null, nome, file);
        Brano branoSalvato = branoRepository.save(brano);
        user.aggiungiBrano(branoSalvato);
        userRepository.save(user);
        return branoSalvato;
    }

}
