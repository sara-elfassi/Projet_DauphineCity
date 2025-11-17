package equipe;

import java.math.BigDecimal;

/**
 * Représente un projet proposé pour la municipalité.
 * <p>
 * Un projet est caractérisé par un titre, une description, un secteur
 * d'activité, un bénéfice et trois coûts (économique, social et environnemental).
 * </p>
 * <p>
 * Les coûts et le bénéfice peuvent être initialement {@code null}
 * et sont remplis progressivement au cours d'un cycle de simulation.
 * </p>
 */

public class Projet {

    private final String titre;
    private final String description;
    private final Secteur secteur;

    private BigDecimal benefice;
    private BigDecimal coutEconomique;
    private BigDecimal coutSocial;
    private BigDecimal coutEnvironnemental;
    
    /**
     * Crée un nouveau projet avec ses attributs (titre, description, secteur).
     *
     * @param titre       le titre du projet (non nul)
     * @param description une description textuelle du projet (non nulle)
     * @param secteur     le secteur d'activité auquel appartient le projet (non nul)
     */

    public Projet(String titre, String description, Secteur secteur) {
        this.titre = titre;
        this.description = description;
        this.secteur = secteur;
    }
    
    /**
     * Renvoie le titre du projet.
     *
     * @return le titre du projet
     */

    public String getTitre() {
        return titre;
    }
    
    /**
     * Renvoie la description du projet.
     *
     * @return la description du projet
     */

    public String getDescription() {
        return description;
    }
    
    /**
     * Renvoie le secteur d'activité du projet.
     *
     * @return le secteur d'activité du projet
     */

    public Secteur getSecteur() {
        return secteur;
    }
    
    /**
    * Renvoie le bénéfice actuellement associé au projet.
    *
    * @return le bénéfice du projet, ou {@code null} s'il n'a pas encore été évalué
    */

    public BigDecimal getBenefice() {
        return benefice;
    }

    public void setBenefice(BigDecimal benefice) {
        this.benefice = benefice;
    }
    
    /**
    * Renvoie le coût économique actuellement associé au projet.
    *
    * @return le coût économique du projet, ou {@code null} s'il n'a pas encore été évalué
    */

    public BigDecimal getCoutEconomique() {
        return coutEconomique;
    }

    public void setCoutEconomique(BigDecimal coutEconomique) {
        this.coutEconomique = coutEconomique;
    }

    /**
     * Renvoie le coût social actuellement associé au projet.
     *
     * @return le coût social du projet, ou {@code null} s'il n'a pas encore été évalué
     */
    
    public BigDecimal getCoutSocial() {
        return coutSocial;
    }

    public void setCoutSocial(BigDecimal coutSocial) {
        this.coutSocial = coutSocial;
    }
    
    /**
    * Renvoie le coût environnemental actuellement associé au projet.
    *
    * @return le coût environnemental du projet, ou {@code null} s'il n'a pas encore été évalué
    */

    public BigDecimal getCoutEnvironnemental() {
        return coutEnvironnemental;
    }

    public void setCoutEnvironnemental(BigDecimal coutEnvironnemental) {
        this.coutEnvironnemental = coutEnvironnemental;
    }

    public boolean estComplet() {
        return benefice != null
               && coutEconomique != null
               && coutSocial != null
               && coutEnvironnemental != null;
    }

    @Override
    public String toString() {
        return "Projet{" +
               "titre='" + titre + '\'' +
               ", secteur = " + secteur +
               ", benefice = " + benefice +
               ", cout economique = " + coutEconomique +
               ", cout social = " + coutSocial +
               ", cout environnemental = " + coutEnvironnemental +
               '}';
    }
}