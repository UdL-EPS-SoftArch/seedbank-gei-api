package cat.udl.eps.softarch.demo.mothers;

import cat.udl.eps.softarch.demo.domain.Seed;
import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.Collections;

public class SeedMother {

    public static Seed getSeed(@Nullable String scientificName, @Nullable String commonName) {
        var seed = new Seed();
        seed.setScientificName(scientificName);
        var commonNames = new ArrayList<>(Collections.singleton(commonName));
        seed.setCommonName(commonNames);
        return seed;
    }

}
