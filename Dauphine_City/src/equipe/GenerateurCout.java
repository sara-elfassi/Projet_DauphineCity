package equipe;

import java.math.BigDecimal;

/**
 * Stratégie de génération de coûts pour les projets.
 * <p>
 * Cette interface permet d'encapsuler la logique
 * utilisée pour produire une valeur de coût pour un type
 * de coût donné ({@link TypeCout}) et un secteur donné
 * ({@link Secteur}). 
 * Les implémentations peuvent utiliser
 * différents modèles (stochastiques, déterministes, etc.).
 * </p>
 */
public interface GenerateurCout {
    
    /**
     * Génère une valeur de coût pour un couple (type de coût, secteur).
     *
     * @param typeCout le type de coût à évaluer (économique, social, environnemental)
     * @param secteur  le secteur du projet considéré
     * @return le coût (potentiellement arrondi)
     */
	BigDecimal genererCout(TypeCout typeCout, Secteur secteur);
}