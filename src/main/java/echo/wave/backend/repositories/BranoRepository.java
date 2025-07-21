package echo.wave.backend.repositories;

import echo.wave.backend.models.Brano;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranoRepository extends JpaRepository<Brano, Long> {
}
