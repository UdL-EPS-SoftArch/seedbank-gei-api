package cat.udl.eps.softarch.demo.mothers;

import cat.udl.eps.softarch.demo.domain.Seed;

import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Collections;

public class SeedMother {

    public static Seed getValidSeedWith(@NotNull String name) {
        var seed = getEmptySeed();
        seed.setScientificName(name);
        return seed;
    }

    public static Seed getValidSeedWith(@NotNull String name, @NotNull Seed ...beneficialFor) {
        var seed = getValidSeedWith(name);
        seed.setBeneficialFor(Arrays.asList(beneficialFor));
        return seed;
    }
    public static Seed getValidSeedWith(@NotNull String name, @NotNull String ...commonNames) {
        var seed = getValidSeedWith(name);
        seed.setCommonName(Arrays.asList(commonNames));
        return seed;
    }

    private static Seed getEmptySeed() {
        var seed = new Seed();
        seed.setCommonName(Collections.emptyList());
        seed.setBeneficialFor(Collections.emptyList());
        return seed;
    }

}
