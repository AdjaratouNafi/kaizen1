package sn.accelsolution.entities;

import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sn.accelsolution.entities.Commande;
import sn.accelsolution.entities.Marche;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-02-25T17:57:56")
@StaticMetamodel(Paiement.class)
public class Paiement_ { 

    public static volatile SingularAttribute<Paiement, BigInteger> montantFacture;
    public static volatile SingularAttribute<Paiement, Integer> idPaiement;
    public static volatile SingularAttribute<Paiement, Date> datePaiement;
    public static volatile SingularAttribute<Paiement, String> observation;
    public static volatile SingularAttribute<Paiement, Marche> idMarche;
    public static volatile SingularAttribute<Paiement, String> modePaiement;
    public static volatile SingularAttribute<Paiement, BigInteger> numeroCheque;
    public static volatile SingularAttribute<Paiement, BigInteger> montantRegle;
    public static volatile SingularAttribute<Paiement, Commande> idCommande;

}