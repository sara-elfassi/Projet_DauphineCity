package equipe;

import java.time.LocalDate;
import java.time.Period;

/**
 * Classe abstraite représentant une personne.
 * <p>
 * Elle fournit les informations de base communes
 * à toutes les personnes (nom, prénom, date de naissance) ainsi
 * qu'une méthode pour calculer l'âge à partir de la date courante.
 * </p>
 * <p>
 * Elle est destinée à être étendue par des classes concrètes
 * qui renvoient à des fonctions dans le conseil municipal :
 * {@link Elu}, {@link EvaluateurCout} ou {@link Expert}.
 * </p>
 */
public abstract class Personne {

    private final String nom;
    private final String prenom;
    private final LocalDate dateNaissance;

    /**
     * Constructeur : recense une nouvelle personne avec son nom, son prénom et sa date de naissance.
     *
     * @param nom           le nom de famille de la personne (non nul)
     * @param prenom        le prénom de la personne (non nul)
     * @param dateNaissance la date de naissance de la personne (non nulle)
     */
    protected Personne(String nom, String prenom, LocalDate dateNaissance) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
    }

    /**
     * Renvoie le nom de famille de la personne.
     *
     * @return Le nom de famille.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Renvoie le prénom de la personne.
     *
     * @return Le prénom.
     */
    public String getPrenom() {
        return prenom;
    } 

    /**
     * Renvoie la date de naissance de la personne.
     *
     * @return La date de naissance.
     */
    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    /**
     * Renvoie l'âge de la personne calculé à partir de la date de naissance et de la date actuelle
     * (fournie par {@link LocalDate#now()}).
     * </p>
     * @return l'âge de la personne (en années).
     */
    public int getAge() {
        return Period.between(dateNaissance, LocalDate.now()).getYears();
    }
    
    /**
     * Renvoie le nom complet de la personne, sous la forme « nom prénom ».
     *
     * @return le nom complet de la personne
     */
    public String getNomComplet() {
        return nom + " " + prenom;
    }

    /**
     * Renvoie une chaine de caractère décrivant la personne.
     * <p>
     * Elle comprend sont nom complet et son âge.
     *
     * @return une chaîne de caractères décrivant la personne
     */
    @Override
    public String toString() {
        return getNomComplet() + " (" + getAge() + " ans)";
    }
}