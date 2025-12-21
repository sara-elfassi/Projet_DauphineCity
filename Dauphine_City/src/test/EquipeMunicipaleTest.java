package test;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class EquipeMunicipaleTest {
    // La classe imbriquée suivante permet de générer un bénéfice fixe pour les tests
    private static class GenerateurBeneficeFixe implements GenerateurBenefice {
        @Override
        public BigDecimal genererBenefice(Projet projet) {
            return BigDecimal.valueOf(1439);
        }
    }
    // La classe imbriquée suivante permet de générer un coût fixe pour les tests
    private static class GenerateurCoutFixe implements GenerateurCout {
        @Override
        public BigDecimal genererCout(TypeCout typeCout, Secteur secteur) {
            return BigDecimal.valueOf(673);
        }
    }

    private EquipeMunicipale construireEquipeAvecUnExpertEtGenerateursDeterministes() {
        Random random = new Random(42);

        GenerateurCout generateurCout = new GenerateurCoutFixe();
        GenerateurBenefice generateurBenefice = new GenerateurBeneficeFixe();

        Elu elu = new Elu("Montand", "Yves",
                LocalDate.of(1980, 5, 10),
                generateurBenefice);

        EvaluateurCout evalEco = new EvaluateurCout("Piaf", "Edith",
                LocalDate.of(1985, 3, 20),
                TypeCout.ECONOMIQUE,
                generateurCout);

        EvaluateurCout evalSocial = new EvaluateurCout("Ferrer", "Nino",
                LocalDate.of(1982, 7, 15),
                TypeCout.SOCIAL,
                generateurCout);

        EvaluateurCout evalEnv = new EvaluateurCout("Hardy", "Françoise",
                LocalDate.of(1990, 1, 5),
                TypeCout.ENVIRONNEMENTAL,
                generateurCout);

        Expert expert = new Expert("Sanson", "Véronique",
                LocalDate.of(1970, 9, 30),
                Set.of(Secteur.SPORT),
                random);

        return new EquipeMunicipale(
                elu, evalEco, evalSocial, evalEnv,
                List.of(expert),
                random
        );
    }

    @Test
    public void executerCycleSimulationCreeUnProjetCompletEtAjouteALaListe() {
        EquipeMunicipale equipe = construireEquipeAvecUnExpertEtGenerateursDeterministes();

        assertEquals(0, equipe.getProjetsEtudies().size());

        Projet projet = equipe.executerCycleSimulation();

        assertTrue(projet.estComplet());
        assertEquals(1, equipe.getProjetsEtudies().size());
        assertSame(projet, equipe.getProjetsEtudies().get(0));
    }

    @Test
    public void executerCycleSimulationSansExpertDoitLeverException() {
        Random random = new Random(42);

        GenerateurCout generateurCout = new GenerateurCoutFixe();
        GenerateurBenefice generateurBenefice = new GenerateurBeneficeFixe();

        Elu elu = new Elu("Montand", "Yves",
                LocalDate.of(1980, 5, 10),
                generateurBenefice);

        EvaluateurCout evalEco = new EvaluateurCout("Piaf", "Edith",
                LocalDate.of(1985, 3, 20),
                TypeCout.ECONOMIQUE,
                generateurCout);

        EvaluateurCout evalSocial = new EvaluateurCout("Ferrer", "Nino",
                LocalDate.of(1982, 7, 15),
                TypeCout.SOCIAL,
                generateurCout);

        EvaluateurCout evalEnv = new EvaluateurCout("Hardy", "Françoise",
                LocalDate.of(1990, 1, 5),
                TypeCout.ENVIRONNEMENTAL,
                generateurCout);

        EquipeMunicipale equipeSansExperts = new EquipeMunicipale(
                elu, evalEco, evalSocial, evalEnv,
                List.of(),
                random
        );

        assertThrows(IllegalStateException.class, equipeSansExperts::executerCycleSimulation);
    }
}
