package equipe;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class GenerateurCoutAleatoireNormalTest {

    @Test
    public void genererCoutRetourneUnMontantValide() {
        Random random = new Random(42);
        GenerateurCout generateur = new GenerateurCoutAleatoireNormal(random);

        BigDecimal cout = generateur.genererCout(TypeCout.ECONOMIQUE, Secteur.SANTE);

        assertAll(
            () -> assertNotNull(cout, "Le coût généré ne doit pas être null"),
            () -> assertTrue(
                    cout.compareTo(BigDecimal.ZERO) >= 0,
                    "Le coût doit être supérieur ou égal à 0"
            ),
            () -> assertEquals(
                    cout,
                    cout.setScale(2, RoundingMode.HALF_UP),
                    "Le coût doit être arrondi à deux décimales"
            )
        );
    }
}