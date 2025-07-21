package echo.wave.backend.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class BranoAggiungiRequest {
    private String nome;
    private String fileName;
    private Long idUser;
    private MultipartFile file;
}
