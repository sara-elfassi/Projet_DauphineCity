package sacADos;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;
import equipe.Projet;
import equipe.Secteur;

public class VersSacADos {

    //  Budgets par type : économique + social + environnemental
    public static SacADos depuisProjets_3Budgets(
            List<Projet> projets,
            int budgetEco,
            int budgetSoc,
            int budgetEnv
    ) {
        List<Objet> objets = new ArrayList<>();
        for (Projet p : projets) {
            int utilite = convert(p.getBenefice());
            int coutEco = convert(p.getCoutEconomique());
            int coutSoc = convert(p.getCoutSocial());
            int coutEnv = convert(p.getCoutEnvironnemental());
            objets.add(new Objet(p.getTitre(), utilite, new int[]{coutEco, coutSoc, coutEnv}));
        }
        int[] budgets = {budgetEco, budgetSoc, budgetEnv};
        return new SacADos(budgets.length, budgets, objets);
    }

    //  Budgets par secteur : 5 secteurs
    public static SacADos depuisProjets_Secteurs(
            List<Projet> projets,
            Map<Secteur, Integer> budgetsParSecteur
    ) {
        List<Objet> objets = new ArrayList<>();
        for (Projet p : projets) {
            int utilite = convert(p.getBenefice());
            int coutEco = convert(p.getCoutEconomique());
            int[] couts = new int[Secteur.values().length];
            Arrays.fill(couts, 0);
            couts[p.getSecteur().ordinal()] = coutEco;
            objets.add(new Objet(p.getTitre(), utilite, couts));
        }

        int[] budgets = new int[Secteur.values().length];
        for (Secteur s : Secteur.values()) {
            budgets[s.ordinal()] = budgetsParSecteur.getOrDefault(s, 0);
        }

        return new SacADos(budgets.length, budgets, objets);
    }

    //  Charger une instance du MKP (Drake 2015)
    public static SacADos depuisFichierMKP(String chemin) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(chemin))) {
            String[] header = br.readLine().trim().split("\\s+");
            int n = Integer.parseInt(header[0]);
            int k = Integer.parseInt(header[1]);

            int[] utilites = new int[n];
            for (int i = 0; i < n; i++) {
                utilites[i] = Integer.parseInt(br.readLine().trim());
            }

            int[][] contraintes = new int[k][n];
            for (int i = 0; i < k; i++) {
                String[] ligne = br.readLine().trim().split("\\s+");
                for (int j = 0; j < n; j++) {
                    contraintes[i][j] = Integer.parseInt(ligne[j]);
                }
            }

            int[] budgets = new int[k];
            String[] b = br.readLine().trim().split("\\s+");
            for (int i = 0; i < k; i++) {
                budgets[i] = Integer.parseInt(b[i]);
            }

            List<Objet> objets = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                int[] couts = new int[k];
                for (int i = 0; i < k; i++) couts[i] = contraintes[i][j];
                objets.add(new Objet("Obj" + j, utilites[j], couts));
            }

            return new SacADos(budgets.length, budgets, objets);
        }
    }

    // conversion BigDecimal -> int
    private static int convert(BigDecimal b) {
        return b == null ? 0 : b.intValue();
    }
}
