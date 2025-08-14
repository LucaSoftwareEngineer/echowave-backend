package echo.wave.backend.comparators;

import echo.wave.backend.models.Brano;
import org.springframework.stereotype.Component;

import java.util.Comparator;

@Component
public class BranoComparatorByNome implements Comparator<Brano> {

    @Override
    public int compare(Brano o1, Brano o2) {
        return o1.getNome().compareTo(o2.getNome());
    }

}
