package equipe;

import java.math.BigDecimal;

/**
 * Stratégie de génération du bénéfice attendu pour un projet.
 * <p>
 * Cette interface permet d'encapsuler la logique métier utilisée
 * pour estimer le bénéfice d'un projet, éventuellement en fonction
 * de ses caractéristiques (par exemple son secteur).
 * </p>
 */
public interface GenerateurBenefice {

    /**
     * Génère une valeur de bénéfice pour un projet.
     *
     * @param projet le projet pour lequel est estimé le bénéfice
     * @return un montant monétaire représentant le bénéfice attendu,
     *         typiquement arrondi à deux décimales
     */
    BigDecimal genererBenefice(Projet projet);
}
