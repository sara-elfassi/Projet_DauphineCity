package equipe;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class GenerateurCoutAleatoireNormalTest {

    @Test
    public void coutGenereDevraitEtrePositifEtDeuxDecimales() {
        Random random = new Random(42);
        GenerateurCout generateur = new GenerateurCoutAleatoireNormal(random);

        BigDecimal cout = generateur.genererCout(TypeCout.ECONOMIQUE, Secteur.SANTE);

        assertNotNull(cout);
        assertTrue(cout.compareTo(BigDecimal.ZERO) >= 0, "Le coût devrait être >= 0");
        assertEquals(2, cout.scale(), "Le coût doit être arrondi à 2 décimales");
    }
}