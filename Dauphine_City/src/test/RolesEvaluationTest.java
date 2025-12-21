package equipe;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class RolesEvaluationTest {

    // La classe imbriquée suivante permet de générer un bénéfice fixe pour les tests
    private static class GenerateurBeneficeFixe implements GenerateurBenefice {
        @Override
        public BigDecimal genererBenefice(Projet projet) {
            return BigDecimal.valueOf(1789);
        }
    }

    // La classe imbriquée suivante permet de générer un coût fixe pour les tests
    private static class GenerateurCoutFixe implements GenerateurCout {
        @Override
        public BigDecimal genererCout(TypeCout typeCout, Secteur secteur) {
            return BigDecimal.valueOf(728);
        }
    }

    @Test
    public void eluDevraitRenseignerLeBenefice() {
        GenerateurBenefice generateurBenefice = new GenerateurBeneficeFixe();
        Elu elu = new Elu("Polnareff", "Michel",
                LocalDate.of(1973, 4, 10),
                generateurBenefice);

        Projet projet = new Projet("Titre", "Desc", Secteur.SPORT);

        assertNull(projet.getBenefice());
        elu.evaluerBenefice(projet);
        assertEquals(BigDecimal.valueOf(1234), projet.getBenefice());
    }

    @Test
    public void evaluateurEconomiqueDevraitRenseignerSeulementCoutEconomique() {
        GenerateurCout generateurCout = new GenerateurCoutFixe();
        EvaluateurCout evalEco = new EvaluateurCout("Balavoine", "Daniel",
                LocalDate.of(1995, 4, 1),
                TypeCout.ECONOMIQUE,
                generateurCout);

        Projet projet = new Projet("Titre", "Desc", Secteur.SANTE);

        evalEco.evaluerCout(projet);

        assertEquals(BigDecimal.valueOf(500), projet.getCoutEconomique());
        assertNull(projet.getCoutSocial());
        assertNull(projet.getCoutEnvironnemental());
    }
}