package sn.accelsolution.entities;

import java.math.BigInteger;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sn.accelsolution.entities.Livraison;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-02-25T17:57:56")
@StaticMetamodel(DetailleLivraison.class)
public class DetailleLivraison_ { 

    public static volatile SingularAttribute<DetailleLivraison, BigInteger> ttc;
    public static volatile SingularAttribute<DetailleLivraison, BigInteger> pu;
    public static volatile SingularAttribute<DetailleLivraison, BigInteger> puhortaxe;
    public static volatile SingularAttribute<DetailleLivraison, Integer> idDetaileLivraison;
    public static volatile SingularAttribute<DetailleLivraison, Integer> qtLivre;
    public static volatile SingularAttribute<DetailleLivraison, String> designation;
    public static volatile SingularAttribute<DetailleLivraison, Livraison> idLivraison;

}