package sn.accelsolution.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sn.accelsolution.entities.Corpsetat;
import sn.accelsolution.entities.Deboursser;
import sn.accelsolution.entities.DetailleCommande;
import sn.accelsolution.entities.Lottechnique;
import sn.accelsolution.entities.Prix;
import sn.accelsolution.entities.Stock;
import sn.accelsolution.entities.Vente;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-02-25T17:57:56")
@StaticMetamodel(Marchandise.class)
public class Marchandise_ { 

    public static volatile SingularAttribute<Marchandise, Corpsetat> idCorpsetat;
    public static volatile SingularAttribute<Marchandise, Integer> idMarchandise;
    public static volatile ListAttribute<Marchandise, Stock> stockList;
    public static volatile ListAttribute<Marchandise, DetailleCommande> detailleCommandeList;
    public static volatile SingularAttribute<Marchandise, String> libelle;
    public static volatile SingularAttribute<Marchandise, String> prixunitaire;
    public static volatile SingularAttribute<Marchandise, Lottechnique> idLottechnique;
    public static volatile ListAttribute<Marchandise, Deboursser> deboursserList;
    public static volatile ListAttribute<Marchandise, Prix> prixList;
    public static volatile ListAttribute<Marchandise, Vente> venteList;

}