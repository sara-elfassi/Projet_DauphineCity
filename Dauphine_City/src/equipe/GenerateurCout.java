package equipe;

import java.math.BigDecimal;

public interface GenerateurCout {
    BigDecimal genererCout(TypeCout typeCout, Secteur secteur);
}