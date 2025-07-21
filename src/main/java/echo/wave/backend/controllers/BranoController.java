package echo.wave.backend.controllers;

import echo.wave.backend.models.Brano;
import echo.wave.backend.services.BranoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/brano")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class BranoController {

    private final BranoService branoService;

    @PostMapping("/aggiungi")
    public Brano aggiungiBrano(
            @RequestPart String nome,
            @RequestPart String fileName,
            @RequestPart String idUser,
            @RequestPart MultipartFile file
    ) throws IOException {
        Long id = Long.parseLong(idUser);
        return branoService.aggiungiBrano(nome, fileName, id, file);
    }

}
