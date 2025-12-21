package test;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class ExpertTest {

    @Test
    public void expertProposeProjetDansSesCompetences() {
        Random random = new Random(42);
        Expert expert = new Expert("Gall", "Françoise",
                LocalDate.of(1964, 8, 10),
                Set.of(Secteur.SPORT, Secteur.SANTE),
                random);

        Projet projet = expert.proposerProjet();

        assertAll(  
        		() -> assertNotNull(projet),
        		() -> assertTrue(expert.getCompetences().contains(projet.getSecteur()),
                "Le secteur du projet doit être parmi les compétences de l'expert"),
        		() -> assertNull(projet.getBenefice()),
        		() -> assertNull(projet.getCoutEconomique()),
        		() -> assertNull(projet.getCoutSocial()),
        		() -> assertNull(projet.getCoutEnvironnemental())
        		);
    }
}

