package equipe;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class ProjetTest {

    @Test
    public void projetEstIncompletAuDebut() {
        Projet projet = new Projet("Titre", "Desc", Secteur.SPORT);

        assertAll(
            () -> assertFalse(projet.estComplet(), "Un projet qui vient d’être créé ne peut pas être complet"),
            () -> assertNull(projet.getBenefice()),
            () -> assertNull(projet.getCoutEconomique()),
            () -> assertNull(projet.getCoutSocial()),
            () -> assertNull(projet.getCoutEnvironnemental())
        );
    }


    @Test
    public void projetEstCompletQuandTousLesChampsSontRenseignes() {
        Projet projet = new Projet("Titre", "Desc", Secteur.SPORT);

        projet.setBenefice(BigDecimal.valueOf(1500));
        projet.setCoutEconomique(BigDecimal.valueOf(500));
        projet.setCoutSocial(BigDecimal.valueOf(350));
        projet.setCoutEnvironnemental(BigDecimal.valueOf(275));

        assertTrue(projet.estComplet(), "Le projet doit être complet lorsque tous les champs sont renseignés");
    }
}

