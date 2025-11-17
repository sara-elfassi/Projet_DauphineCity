package equipe;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Main_Equipe {

    public static void main(String[] args) {

        Random randomGlobal = new Random(100);

        GenerateurCout generateurCout =
                new GenerateurCoutAleatoireNormal(randomGlobal);
        GenerateurBenefice generateurBenefice =
                new GenerateurBeneficeAleatoireNormal(randomGlobal);

        Elu elu = new Elu("Delerue", "Eloise",
                LocalDate.of(1980, 5, 10),
                generateurBenefice);

        EvaluateurCout evalEco = new EvaluateurCout("Bernard", "Titien",
                LocalDate.of(1985, 3, 20),
                TypeCout.ECONOMIQUE,
                generateurCout);

        EvaluateurCout evalSocial = new EvaluateurCout("Lapray", "Lucile",
                LocalDate.of(1982, 7, 15),
                TypeCout.SOCIAL,
                generateurCout);

        EvaluateurCout evalEnv = new EvaluateurCout("Gariazzo", "Emilie",
                LocalDate.of(1990, 1, 5),
                TypeCout.ENVIRONNEMENTAL,
                generateurCout);

        Expert expert1 = new Expert("Touileb", "Yacine",
                LocalDate.of(1970, 9, 30),
                Set.of(Secteur.SPORT, Secteur.SANTE),
                randomGlobal);

        Expert expert2 = new Expert("Rebours", "Lena",
                LocalDate.of(1968, 11, 12),
                Set.of(Secteur.CULTURE, Secteur.EDUCATION, Secteur.ATTRACTIVITE_ECONOMIQUE),
                randomGlobal);

        EquipeMunicipale equipe = new EquipeMunicipale(
                elu,
                evalEco,
                evalSocial,
                evalEnv,
                List.of(expert1, expert2),
                randomGlobal
        );

        equipe.executerNCycles(5);

        for (Projet p : equipe.getProjetsEtudies()) {
            System.out.println(p);
        }
    }
}
